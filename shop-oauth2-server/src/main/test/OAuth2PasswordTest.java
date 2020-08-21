import javafx.application.Application;
import link.net.shop.system.security.SecurityServerSystemApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/8/20 9:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityServerSystemApplication.class)
public class OAuth2PasswordTest {
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Test
    public  void passwordEncode() {
        //secret
        System.out.println(passwordEncoder.encode("user-secret-8888"));
    }
}
