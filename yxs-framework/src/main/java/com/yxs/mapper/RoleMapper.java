package com.yxs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxs.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    List<Long> selectRoleIdByUserId(Long userId);

}

