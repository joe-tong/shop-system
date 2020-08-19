package link.net.shop.system.user.repository.admin;

import link.net.shop.system.user.model.admin.ShopAdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ShopAdminUserRepository extends JpaRepository<ShopAdminUser, String> {
}
