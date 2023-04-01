package com.yxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.dto.TagListDto;
import com.yxs.domain.entity.Tag;
import com.yxs.domain.vo.PageVo;
import com.yxs.domain.vo.TagVo;

import java.util.List;

public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    List<TagVo> listAllTag();

}
