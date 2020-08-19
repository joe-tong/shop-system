package link.net.shop.system.mq.kafka.model;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "kafka_in_msg")
public class KafkaInMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fwBh;

    private Date gmtCreate;

    private Date gmtUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFwBh() {
        return fwBh;
    }

    public void setFwBh(Long fwBh) {
        this.fwBh = fwBh;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }
}