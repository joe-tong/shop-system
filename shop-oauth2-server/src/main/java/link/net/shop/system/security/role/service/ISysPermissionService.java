package link.net.shop.system.security.role.service;



import link.net.shop.system.security.role.entity.SysPermission;

import java.util.List;

/**
 * Created by smlz on 2019/12/20.
 */
public interface ISysPermissionService {

    List<SysPermission> findByUserId(Integer userId);
}
