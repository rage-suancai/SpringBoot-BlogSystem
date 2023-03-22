package com.yxs.job;

import com.yxs.constants.SystemConstants;
import com.yxs.domain.entity.Article;
import com.yxs.service.ArticleService;
import com.yxs.utils.RedisCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author YXS
 * @PackageName: com.yxs.job
 * @ClassName: UpdateViewCountJob
 * @Desription:
 * @date 2023/3/22 15:27
 */
@Component
public class UpdateViewCountJob {

    @Resource
    private RedisCache redisCache;
    @Resource
    private ArticleService articleService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void updateViewCount() {

        Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.VIEW_COUNT); // 获取redis中的浏览量

        List<Article> articles = viewCountMap.entrySet().stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        articleService.updateBatchById(articles); // 更新到数据库

    }

}
