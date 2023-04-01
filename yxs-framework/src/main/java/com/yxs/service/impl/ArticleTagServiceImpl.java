package com.yxs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.domain.entity.ArticleTag;
import com.yxs.mapper.ArticleTagMapper;
import com.yxs.service.ArticleTagService;
import org.springframework.stereotype.Service;

@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {



}

