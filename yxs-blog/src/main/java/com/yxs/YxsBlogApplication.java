package com.yxs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author YXS
 * @PackageName: com.yxs
 * @ClassName: YxsBlogApplication
 * @Desription:
 * @date 2023/3/17 23:30
 */
@EnableSwagger2
@EnableScheduling
// @MapperScan("com.yxs.mapper")
@SpringBootApplication
public class YxsBlogApplication {

    public static void main(String[] args) {

        SpringApplication.run(YxsBlogApplication.class, args);

    }

}
