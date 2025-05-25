package com.ssafy.ssafit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${profile.upload.dir}")  // C:/.../uploads
    private String profileUploadDir;

    @Value("${event.upload.dir}")    // C:/.../uploads/events
    private String eventUploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 프로필 이미지 매핑
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + profileUploadDir + "/");

        // 이벤트 이미지 매핑 
        registry.addResourceHandler("/uploads/events/**")
                .addResourceLocations("file:" + eventUploadDir + "/");
    }
}