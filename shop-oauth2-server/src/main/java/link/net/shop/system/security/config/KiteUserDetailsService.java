package link.net.shop.system.security.config;

import link.net.shop.system.security.bean.User;
import link.net.shop.system.security.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component(value = "kiteUserDetailsService")
public class KiteUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    /**
     * Security的登录，User赋予权限
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("the username is not null");
        }
        //校验用户是否存在
        User user = userRepository.getById(username);
        if (null == user){
            throw new UsernameNotFoundException("the user is not exist");
        }

        //给用户添加角色权限
        String role = user.getRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        //返回用户token
        return new org.springframework.security.core.userdetails.User(username, user.getOauthpassword(), authorities);
    }
}