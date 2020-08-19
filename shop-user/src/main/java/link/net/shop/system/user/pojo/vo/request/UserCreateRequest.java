package link.net.shop.system.user.pojo.vo.request;

import lombok.Data;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/24 15:17
 */
@Data
public class UserCreateRequest {
    private String idCard;
    private String tel;
    private String name;
}
