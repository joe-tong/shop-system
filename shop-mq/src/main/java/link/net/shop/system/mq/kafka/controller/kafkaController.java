package link.net.shop.system.mq.kafka.controller;

import io.swagger.annotations.Api;
import link.net.shop.system.mq.kafka.service.impl.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/31 10:36
 */
@Api(tags = "kafka")
@RestController
public class kafkaController {
    @Autowired
    private KafkaService kafkaService;

    @GetMapping("/send/message")
    public String sentKafkaMessage(String msg, String topic) {
        kafkaService.sendMessage(topic, msg);
        return "发送成功";
    }
}
