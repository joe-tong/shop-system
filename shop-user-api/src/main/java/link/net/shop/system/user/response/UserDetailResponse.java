package link.net.shop.system.user.response;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/24 14:23
 */
@Data
@Builder
public class UserDetailResponse {
    private String userName;
    private String tel;
    private String sex;
}
