package com.yxs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxs.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YXS
 * @PackageName: com.yxs.mapper
 * @ClassName: UserMapper
 * @Desription:
 * @date 2023/3/20 9:14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    

}
