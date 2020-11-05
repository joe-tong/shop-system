package link.net.shop.system.user.controller;

import io.swagger.annotations.Api;
import link.net.shop.system.common.response.ServerResponse;
import link.net.shop.system.user.model.admin.ShopAdminUser;
import link.net.shop.system.user.model.user.ShopUser;
import link.net.shop.system.user.pojo.vo.request.AdminCreateRequest;
import link.net.shop.system.user.pojo.vo.request.QueryUserRequest;
import link.net.shop.system.user.pojo.vo.request.UserCreateRequest;
import link.net.shop.system.user.pojo.vo.response.UserInfoResponse;
import link.net.shop.system.user.repository.admin.ShopAdminUserRepository;
import link.net.shop.system.user.repository.user.UserRepository;
import link.net.shop.system.user.response.UserDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/23 18:43
 */

@Api(tags = "用户")
@RestController
public class UserController {

    @Autowired
    private ShopAdminUserRepository shopAdminUserRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("query/detail")
    public ServerResponse<UserInfoResponse> queryUser(QueryUserRequest request) {
        UserInfoResponse response = UserInfoResponse.builder()
                .userName(null)
                .tel(null)
                .build();

        return ServerResponse.success(response);

    }

    @GetMapping("detail")
    public ServerResponse<UserDetailResponse> queryDetailUser(String userId) {
        UserDetailResponse response = UserDetailResponse.builder()
                .userName("童平平")
                .tel("15797862806")
                .build();

        return ServerResponse.success(response);

    }

    @PostMapping("create/admin")
    public ServerResponse createAdmin(@RequestBody AdminCreateRequest request) {
        ShopAdminUser admin = new ShopAdminUser();
        admin.setName(request.getName());
        admin.setTel(request.getTel());
        shopAdminUserRepository.save(admin);
        int i = 1;
        i = 1 / 0;

        return ServerResponse.success();
    }

    @PostMapping("create/user")
    public ServerResponse createUser(@RequestBody UserCreateRequest request) {
        ShopUser shopUser = new ShopUser();
        shopUser.setName(request.getName());
        shopUser.setTel(request.getTel());
        shopUser.setIdCard(request.getIdCard());
        userRepository.save(shopUser);

        return ServerResponse.success();
    }
}
