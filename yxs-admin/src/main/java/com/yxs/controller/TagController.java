package com.yxs.controller;

import com.yxs.domain.ResponseResult;
import com.yxs.domain.dto.AddTagDto;
import com.yxs.domain.dto.EditTagDto;
import com.yxs.domain.dto.TagListDto;
import com.yxs.domain.entity.Tag;
import com.yxs.service.TagService;
import com.yxs.utils.BeanCopyUtils;
import com.yxs.domain.vo.TagVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: TagController
 * @Desription:
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult listTag(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody AddTagDto addTagDto) {

        Tag tag = BeanCopyUtils.copyBean(addTagDto, Tag.class);
        tagService.save(tag);
        return ResponseResult.okResult();

    }

    @GetMapping(value = "/{id}")
    public ResponseResult getTagInfo(@PathVariable(value = "id") Long id) {
        return ResponseResult.okResult(tagService.getById(id));
    }

    @PutMapping
    public ResponseResult editTag(@RequestBody EditTagDto editTagDto) {

        Tag tag = BeanCopyUtils.copyBean(editTagDto, Tag.class);
        tagService.updateById(tag);
        return ResponseResult.okResult();

    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag() {
        return ResponseResult.okResult(tagService.listAllTag());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseResult deleteTag(@PathVariable Long id) {

        tagService.removeById(id);
        return ResponseResult.okResult();

    }

}
