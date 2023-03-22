package com.yxs.runner;

import com.yxs.constants.SystemConstants;
import com.yxs.domain.entity.Article;
import com.yxs.mapper.ArticleMapper;
import com.yxs.utils.RedisCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author YXS
 * @PackageName: com.yxs.runner
 * @ClassName: ViewCountRunner
 * @Desription:
 * @date 2023/3/22 9:35
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {

        List<Article> articles = articleMapper.selectList(null); // 查询博客信息
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        redisCache.setCacheMap(SystemConstants.VIEW_COUNT, viewCountMap);

    }

}
