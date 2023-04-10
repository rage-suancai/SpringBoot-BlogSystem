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
@TableName("sg_link")
public class Link {

    @TableId
    private Long id;
    
    private String name;
    
    private String logo;
    
    private String description;

    private String address; // 网站地址

    private String status; // 审核状态(0代表审核通过 1代表审核未通过 2代表未审核)
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;

    private Integer delFlag; // 删除标志(0代表未删除 1代表已删除)

}

