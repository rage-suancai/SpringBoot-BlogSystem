package com.yxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Category;

public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

}
