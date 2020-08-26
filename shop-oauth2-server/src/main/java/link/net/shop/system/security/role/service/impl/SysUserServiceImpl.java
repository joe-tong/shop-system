package link.net.shop.system.security.role.service.impl;


import link.net.shop.system.security.role.entity.SysUser;
import link.net.shop.system.security.role.mapper.SysUserMapper;
import link.net.shop.system.security.role.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/12/20.
 */
@Component
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getByUsername(String username) {
        return sysUserMapper.findByUserName(username);
    }
}
