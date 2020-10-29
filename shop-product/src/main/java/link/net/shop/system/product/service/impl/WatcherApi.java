package link.net.shop.system.product.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;


/**
 * @author : joe
 * @desc :
 * @date : 2020年10月29日 2:34 下午
 **/
@Slf4j
public class WatcherApi implements Watcher {

    @Override
    public void process(WatchedEvent event) {
        log.info("【Watcher监听事件】={}",event.getState());
        log.info("【监听路径为】={}",event.getPath());
        log.info("【监听的类型为】={}",event.getType()); //  三种监听类型： 创建，删除，更新
    }
}

