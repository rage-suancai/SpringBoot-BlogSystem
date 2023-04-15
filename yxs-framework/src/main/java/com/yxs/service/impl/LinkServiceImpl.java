package com.yxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.constants.SystemConstants;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Link;
import com.yxs.domain.vo.LinkVo;
import com.yxs.mapper.LinkMapper;
import com.yxs.service.LinkService;
import com.yxs.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: LinkServiceImpl
 * @Desription:
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {

        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL); // 查询所有审批通过的
        List<Link> links = list(queryWrapper);
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        return ResponseResult.okResult(linkVos);

    }

}
