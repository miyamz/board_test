package com.miyam.mBoarder.common;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        List<String> URL_PATTERNS = Arrays.asList("/**");
		registry.addInterceptor(new HttpInterceptor())
			.addPathPatterns(URL_PATTERNS);
			//.excludePathPatterns("/제외패턴")
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/image/**",
                "/css/**",
                "/js/**",
                "/fonts/**",
                "/media/**",
                "/vendor/**",
                "/static/**")
                .addResourceLocations(
                        "classpath:/common/image/",
                        "classpath:/common/css/",
                        "classpath:/common/js/",
                        "classpath:/common/fonts/",
                        "classpath:/common/media/",
                        "classpath:/common/vendor/",
                        "classpath:/common/static/");
    }
}
