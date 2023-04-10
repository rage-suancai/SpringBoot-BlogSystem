package com.yxs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxs.domain.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenTreeByUserId(Long userId);

    List<Long> selectMenuListByRoleId(Long roleId);

}

