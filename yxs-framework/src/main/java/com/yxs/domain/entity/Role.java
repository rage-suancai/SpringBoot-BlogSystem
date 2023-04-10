package com.yxs.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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

    private String delFlag; // 删除标志(0代表存在 2代表删除)

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String remark; // 备注

    @TableField(exist = false) // 关联菜单id数组 不是表中的字段 用来接收参数使用
    private Long[] menuIds;

}

