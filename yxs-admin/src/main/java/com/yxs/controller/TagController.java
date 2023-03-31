package com.yxs.controller;

import com.yxs.domain.ResponseResult;
import com.yxs.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: TagController
 * @Desription:
 * @date 2023/3/22 17:54
 */
@RestController
@RequestMapping("/content")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list() {

        return ResponseResult.okResult(tagService.list());

    }

}
