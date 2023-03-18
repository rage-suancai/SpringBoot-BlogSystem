package com.yxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Link;

public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

}
