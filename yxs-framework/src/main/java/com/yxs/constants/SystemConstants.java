package com.yxs.constants;

/**
 * @author YXS
 * @PackageName: com.yxs.constants
 * @ClassName: SystemConstants
 * @Desription:
 * @date 2023/3/18 2:49
 */
public class SystemConstants {

    public static final int ARTICLE_STATUS_DRAFT =  1; // 草稿

    public static final int ARTICLE_STATUS_NORMAL = 0; // 正常文章

    public static final String STATUS_NORMAL = "0"; // 状态

    public static final String LINK_STATUS_NORMAL = "0";

    public static final String ARTICLE_COMMENT = "0"; // 评论类型: 文章评论

    public static final String LINK_COMMENT = "1"; // 评论类型: 友联评论

    public static final String VIEW_COUNT = "article:viewCount"; // redis的key

}
