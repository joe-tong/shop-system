package link.net.shop.system.seckill.service.impl;

import link.net.shop.system.seckill.common.dynamicquery.DynamicQuery;
import link.net.shop.system.seckill.common.entity.RedPacket;
import link.net.shop.system.seckill.common.entity.RedPacketRecord;
import link.net.shop.system.seckill.common.entity.Result;
import link.net.shop.system.seckill.common.redis.RedisUtil;
import link.net.shop.system.seckill.distributedlock.redis.RedissLockUtil;
import link.net.shop.system.seckill.service.IRedPacketService;
import link.net.shop.system.seckill.web.RedPacketController;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service("redPacketService")
public class RedPacketService implements IRedPacketService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedPacketService.class);
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private DynamicQuery dynamicQuery;

    volatile boolean res = false;

    @Override
    public RedPacket get(long redPacketId) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result startSeckil(long redPacketId, int userId) {
        Integer money = 0;
        boolean res = false;
        try {
            /**
             * 获取锁
             */
            res = RedissLockUtil.tryLock(redPacketId + "", TimeUnit.SECONDS, 3, 10);
            if (res) {
                long count = Long.parseLong(redisUtil.getValue(redPacketId + "-num").toString());
                /**
                 * 如果是最后一人
                 */
                if (count == 0) {
                    money = Integer.parseInt(redisUtil.getValue(redPacketId + "-money").toString());
                } else {
                    Integer restMoney = Integer.parseInt(redisUtil.getValue(redPacketId + "-money").toString());
                    Random random = new Random();
                    //随机范围：[1,剩余人均金额的两倍]
                    money = random.nextInt((int) (restMoney / (count + 1) * 2 - 1)) + 1;
                }
                redisUtil.decr(redPacketId + "-money", money);
                /**
                 * 异步入库
                 */
                RedPacketRecord record = new RedPacketRecord();
                record.setMoney(money);
                record.setRedPacketId(redPacketId);
                record.setUid(userId);
                record.setCreateTime(new Timestamp(System.currentTimeMillis()));
                saveRecord(record);
                /**
                 * 异步入账
                 */
            } else {
                /**
                 * 获取锁失败相当于抢红包失败，红包个数加一
                 */
                redisUtil.incr(redPacketId + "-num", 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            if (res) {
                RedissLockUtil.unlock(redPacketId + "");
            }
        }
        return Result.ok(money);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result startTwoSeckil(long redPacketId, int userId) {
        Integer money = 0;
        try {
            /**
             * 获取锁 保证红包数量和计算红包金额的原子性操作
             *  如果redis的num是10，只有10个通过，这里应该控制了并发
             */

            long restPeople = redisUtil.decr(redPacketId + "-num", 1);
            if (restPeople < 0) {
                redisUtil.incr(redPacketId + "-num", 1);
                return Result.error("手慢了，红包" + redPacketId + "派完了");
            }
            LOGGER.info("红包{}只剩下{}个", redPacketId, restPeople);
            RedissLockUtil.lock(redPacketId + "");

            LOGGER.info("成功加锁{}", Thread.currentThread().getName());

            /**
             * 如果是最后一人
             */
            if (restPeople == 0) {
                money = Integer.parseInt(redisUtil.getValue(redPacketId + "-money").toString());
            } else {
                Integer restMoney = Integer.parseInt(redisUtil.getValue(redPacketId + "-money").toString());
                Random random = new Random();
                //随机范围：[1,剩余人均金额的两倍]
                money = random.nextInt((int) (restMoney / (restPeople + 1) * 2 - 1)) + 1;
            }
            redisUtil.decr(redPacketId + "-money", money);
            /**
             * 异步入库
             */
            RedPacketRecord record = new RedPacketRecord();
            record.setMoney(money);
            record.setRedPacketId(redPacketId);
            record.setUid(userId);
            record.setCreateTime(new Timestamp(System.currentTimeMillis()));
            saveRecord(record);
            /**
             * 异步入账
             */
            LOGGER.info("抢到🧧了");
        } catch (Exception e) {
            LOGGER.error("下单异常", e);
        } finally {
            LOGGER.info("成功释放锁{}", Thread.currentThread().getName());
            RedissLockUtil.unlock(redPacketId + "");
        }
        return Result.ok(money);
    }

    @Async
    void saveRecord(RedPacketRecord record) {
        dynamicQuery.save(record);
    }
}
