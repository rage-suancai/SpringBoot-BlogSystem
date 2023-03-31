package com.yxs.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
public class Role {

    @TableId
    private Long id; // 角色ID

    private String roleName; // 角色名称

    private String roleKey; // 角色权限字符串

    private Integer roleSort; // 显示顺序

    private String status; // 角色状态(0正常 1停用)

    private String delFlag; // 删除标志(0代表存在 1代表删除)

    private Long createBy; // 创建者

    private Date createTime; // 创建时间

    private Long updateBy; // 更新者

    private Date updateTime; // 更新时间

    private String remark; // 备注

}

