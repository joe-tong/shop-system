package link.net.shop.system.mq.kafka.service;

/**
 * @author liBai
 * @Classname SendMsgService
 * @Description TODO
 * @Date 2019-05-14 15:02
 */
public interface SendMsgService {
    /**
     * 获取队列消息
     * @return
     */
    String getMsg();

    /**
     * 获取消息使用的此方法，getMsg反应慢
     * @return
     */
    String getMsg2();

    /**
     * 修改消息一读壮体啊
     * @param fuBh
     */
    void resolve(Long fuBh);
}
