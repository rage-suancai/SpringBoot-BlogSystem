package com.yxs.utils;

import com.yxs.domain.entity.Article;
import com.yxs.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YXS
 * @PackageName: com.yxs.utils
 * @ClassName: BeanCopyUtils
 * @Desription:
 * @date 2023/3/18 2:53
 */
public class BeanCopyUtils {

    private BeanCopyUtils() { }

    public static <V>V copyBean(Object source, Class<V> clazz) {

        V result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result); // 属性copy
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    public static <O,V>List<V> copyBeanList(List<O> list, Class<V> clazz) {

        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());

    }

    /*public static void main(String[] args) {

        Article article = new Article();
        article.setId(1L);
        article.setTitle("ss");
        HotArticleVo hotArticleVo = copyBean(article, HotArticleVo.class);
        System.out.println(hotArticleVo);

    }*/

}
