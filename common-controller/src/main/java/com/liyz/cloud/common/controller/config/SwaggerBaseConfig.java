package com.liyz.cloud.common.controller.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/**
 * 注释: swagger配置基类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/7 18:45
 */
public class SwaggerBaseConfig {

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringCloud-Application-API")
                .description("分布式SpringCloud项目")
                .termsOfServiceUrl("http://127.0.0.1:8093/")
                .contact(new Contact("liyangzhen", "https://github.com/ZhiQinIsZhen", "liyangzhen0114@foxmail.com"))
                .version("1.0")
                .build();

    }
}
