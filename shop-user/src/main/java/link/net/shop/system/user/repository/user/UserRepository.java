package link.net.shop.system.user.repository.user;

import link.net.shop.system.user.model.user.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ShopUser, String> {
}
