package com.yxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.dto.TagListDto;
import com.yxs.domain.entity.Tag;
import com.yxs.domain.vo.PageVo;
import com.yxs.domain.vo.TagVo;
import com.yxs.mapper.TagMapper;
import com.yxs.service.TagService;
import com.yxs.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: TagServiceImpl
 * @Desription:
 * @date 2023/3/22 17:46
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {

        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName()); // 分页查询
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());
        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum); page.setSize(pageSize);
        page(page, queryWrapper);

        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal()); // 封装数据返回
        return ResponseResult.okResult(pageVo);

    }

    @Override
    public List<TagVo> listAllTag() {

        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();

        wrapper.select(Tag::getId, Tag::getName);
        List<Tag> list = list(wrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(list, TagVo.class);
        return tagVos;

    }

}
