package link.net.shop.system.user.api;

import link.net.shop.system.common.response.ServerResponse;
import link.net.shop.system.user.request.AdminCreateRequest;
import link.net.shop.system.user.response.UserDetailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-server", path = "user")
public interface UserFeignApi {

    /**
     * 获取用户详情
     *
     * @param userId 用户ID
     * @return
     */
    @GetMapping("detail")
    ServerResponse<UserDetailResponse> queryUserDetail(@RequestParam String userId);

    @PostMapping("create/admi")
    ServerResponse createAdmin(@RequestBody AdminCreateRequest request);


}
