package com.yxs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.domain.entity.Tag;
import com.yxs.mapper.TagMapper;
import com.yxs.service.TagService;
import org.springframework.stereotype.Service;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: TagServiceImpl
 * @Desription:
 * @date 2023/3/22 17:46
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {



}
