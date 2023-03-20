package com.yxs.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author YXS
 * @PackageName: com.yxs.vo
 * @ClassName: UserInfoVo
 * @Desription:
 * @date 2023/3/19 3:28
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {

    private Long id; // 主键

    private String nickName; // 昵称

    private String avatar; // 头像

    private String sex;

    private String email;

}
