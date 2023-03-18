package com.yxs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.User;
import com.yxs.mapper.BlogLoginMapper;
import com.yxs.service.BlogLoginService;
import org.springframework.stereotype.Service;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: UserServiceImpl
 * @Desription:
 * @date 2023/3/19 2:15
 */
@Service
public class BlogLoginServiceImpl extends ServiceImpl<BlogLoginMapper, User> implements BlogLoginService {


    @Override
    public ResponseResult login(User user) {
        return null;
    }

}
