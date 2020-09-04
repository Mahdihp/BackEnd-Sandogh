package com.mahdi.sandogh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by: mahdi
 * DateTime: ۲۰۲۰/09/04 - 14:42:19
 * Email: mahdip.devsc@gmail.com
 * http://github.com/mahdihp & http://gitlab.com/mahdihp
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Can just allow `methods` that you need.
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST")
                .allowedHeaders("Access-Control-Allow-Methods")
                .allowedHeaders("‘Access-Control-Allow-Origin",
                        "Authorization",
                        "Content-Type");
    }
}
