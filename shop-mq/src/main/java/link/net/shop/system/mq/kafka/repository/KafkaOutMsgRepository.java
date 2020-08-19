package link.net.shop.system.mq.kafka.repository;


import link.net.shop.system.mq.kafka.model.KafkaOutMsg;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/31 12:18
 */
public interface KafkaOutMsgRepository extends JpaRepository<KafkaOutMsg, Long> {
}
