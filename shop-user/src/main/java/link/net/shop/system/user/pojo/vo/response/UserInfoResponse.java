package link.net.shop.system.user.pojo.vo.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/23 19:07
 */
@Data
@Builder
public class UserInfoResponse {
    private String userName;
    private String tel;
}
