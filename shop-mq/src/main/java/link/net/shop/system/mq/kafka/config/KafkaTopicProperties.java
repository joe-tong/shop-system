package link.net.shop.system.mq.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/8/26 11:42
 */
@ConfigurationProperties("shop-mall.kafka.topic")
public class KafkaTopicProperties implements Serializable {

    private String groupId;
    private String[] topicName;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String[] getTopicName() {
        return topicName;
    }

    public void setTopicName(String[] topicName) {
        this.topicName = topicName;
    }
}