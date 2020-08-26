package link.net.shop.system.security.role.service;


import link.net.shop.system.security.role.entity.SysUser;

/**
 * Created by smlz on 2019/12/20.
 */
public interface ISysUserService {

    SysUser getByUsername(String username);
}
