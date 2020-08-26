package link.net.shop.system.security.role.mapper;


import link.net.shop.system.security.role.entity.SysUser;

/**
 * Created by smlz on 2019/12/20.
 */
public interface SysUserMapper {

    SysUser findByUserName(String userName);
}
