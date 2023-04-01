package com.yxs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author YXS
 * @PackageName: com.yxs.vo
 * @ClassName: AdminUserInfoVo
 * @Desription:
 * @date 2023/3/23 2:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AdminUserInfoVo {

    private List<String> permissions;

    private List<String> roles;

    private  UserInfoVo user;

}
