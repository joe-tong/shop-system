package link.net.shop.system.security.repository;

import link.net.shop.system.security.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/8/20 16:13
 */
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select r from User r where r.id = ?1 ")
    User getById(String username);

}
