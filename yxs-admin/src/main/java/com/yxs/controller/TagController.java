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
 * @date 2023/3/22 17:54
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

        Tag tag = tagService.getById(id);
        return ResponseResult.okResult(tag);

    }

    @PutMapping
    public ResponseResult editTag(@RequestBody EditTagDto editTagDto) {

        Tag tag = BeanCopyUtils.copyBean(editTagDto, Tag.class);
        tagService.updateById(tag);
        return ResponseResult.okResult();

    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag() {

        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);

    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable Long id) {

        tagService.removeById(id);
        return ResponseResult.okResult();

    }

}
