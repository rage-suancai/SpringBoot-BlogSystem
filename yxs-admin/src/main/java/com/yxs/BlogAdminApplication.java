package com.yxs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author YXS
 * @PackageName: com.yxs
 * @ClassName: BlogAdminApplication
 * @Desription:
 * @date 2023/3/22 16:51
 */
@EnableSwagger2
@SpringBootApplication
public class BlogAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }

}
