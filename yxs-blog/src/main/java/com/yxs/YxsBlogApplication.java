package com.yxs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author YXS
 * @PackageName: com.yxs
 * @ClassName: YxsBlogApplication
 * @Desription:
 */
@EnableSwagger2
@EnableScheduling
@SpringBootApplication
public class YxsBlogApplication {

    public static void main(String[] args) {

        SpringApplication.run(YxsBlogApplication.class, args);

    }

}
