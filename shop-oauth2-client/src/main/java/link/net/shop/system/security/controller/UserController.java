package link.net.shop.system.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/8/19 15:52
 */
@Slf4j
@RestController
public class UserController {

    @GetMapping(value = "get")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Object get(OAuth2Authentication authentication){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        authentication.getCredentials();
//        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
//        String token = details.getTokenValue();

        return authentication.getName();
    }
}