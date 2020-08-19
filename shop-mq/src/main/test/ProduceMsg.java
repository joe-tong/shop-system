import link.net.shop.system.MqSystemApplication;
import link.net.shop.system.mq.kafka.constant.Topic;
import link.net.shop.system.mq.kafka.producer.KafKaConsumerProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/31 15:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MqSystemApplication.class)
public class ProduceMsg {

    @Autowired
    private KafKaConsumerProducer kafKaConsumerProducer;

    @Test
    public void test() throws InterruptedException {
        kafKaConsumerProducer.sendMessage(Topic.SIMPLE,"produce msg");

        Thread.sleep(15000);
    }
}
