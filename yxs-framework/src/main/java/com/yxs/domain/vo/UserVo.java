package com.yxs.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserVo {

    private Long id; // 主键

    private String userName; // 用户名

    private String nickName; // 昵称

    private String status; // 账号状态(0正常 1停用)

    private String email; // 邮箱

    private String phonenumber; // 手机号

    private String sex; // 用户性别(0男 1女 2未知)

    private String avatar; // 头像

    private Long createBy; // 创建人的用户id

    private Date createTime; // 创建时间

    private Long updateBy; // 更新人

    private Date updateTime; // 更新时间

}
