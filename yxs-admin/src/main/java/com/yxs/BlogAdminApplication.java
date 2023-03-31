package com.yxs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YXS
 * @PackageName: com.yxs
 * @ClassName: BlogAdminApplication
 * @Desription:
 * @date 2023/3/22 16:51
 */
// @MapperScan("com.yxs.mapper")
@SpringBootApplication
public class BlogAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }

}
