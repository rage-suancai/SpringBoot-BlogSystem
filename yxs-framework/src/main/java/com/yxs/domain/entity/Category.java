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
@TableName("sg_category")
public class Category {

    @TableId
    private Long id;

    private String name; // 分类名

    private Long pid; // 父分类id 如果没有父分类为-1

    private String description; // 描述

    private String status; // 状态0: 正常 1禁用
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;

    private Integer delFlag; // 删除标志(0代表未删除 1代表已删除)

}

