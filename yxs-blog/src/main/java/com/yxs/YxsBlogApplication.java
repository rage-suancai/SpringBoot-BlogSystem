package com.yxs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YXS
 * @PackageName: com.yxs
 * @ClassName: YxsBlogApplication
 * @Desription:
 * @date 2023/3/17 23:30
 */
@SpringBootApplication
@MapperScan("com.yxs.mapper")
public class YxsBlogApplication {

    public static void main(String[] args) {

        SpringApplication.run(YxsBlogApplication.class, args);

    }

}
