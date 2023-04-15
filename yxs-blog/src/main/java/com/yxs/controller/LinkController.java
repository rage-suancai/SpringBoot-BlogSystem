package com.yxs.controller;

import com.yxs.domain.ResponseResult;
import com.yxs.service.LinkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: LinkController
 * @Desription:
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private LinkService linkService;

    @GetMapping("/getAllLink")
    public final ResponseResult getAllLink() {
        return linkService.getAllLink();
    }

}
