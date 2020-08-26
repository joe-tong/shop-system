package link.net.shop.system.security.config;

import com.alibaba.fastjson.JSON;
import link.net.shop.system.security.role.domin.TulingUser;
import link.net.shop.system.security.role.entity.SysPermission;
import link.net.shop.system.security.role.entity.SysUser;
import link.net.shop.system.security.role.mapper.SysUserMapper;
import link.net.shop.system.security.role.service.ISysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component(value = "kiteUserDetailsService")
public class KiteUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysPermissionService sysPermissionService;

    /**
     * Security的登录，User赋予权限
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.findByUserName(username);

        if(null == sysUser) {
            log.warn("根据用户名:{}查询用户信息为空",username);
            throw new UsernameNotFoundException(username);
        }

        List<SysPermission> sysPermissionList = sysPermissionService.findByUserId(sysUser.getId());

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sysPermissionList)) {
            for (SysPermission sysPermission : sysPermissionList) {
                authorityList.add(new SimpleGrantedAuthority(sysPermission.getUri()));
            }
        }

        TulingUser tulingUser = new TulingUser(sysUser.getUsername(),passwordEncoder.encode(sysUser.getPassword()),authorityList);
        log.info("用户登陆成功:{}", JSON.toJSONString(tulingUser));
        return tulingUser;
    }
}