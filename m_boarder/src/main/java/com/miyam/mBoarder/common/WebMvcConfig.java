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
                "/img/**",
                "/css/**",
                "/js/**",
                "/scss/**",
                "/vendor/**")
                .addResourceLocations(
                        "classpath:/WEB-INF/common/img/",
                        "classpath:/WEB-INF/common/css/",
                        "classpath:/WEB-INF/common/js/",
                        "classpath:/WEB-INF/common/scss/",
                        "classpath:/WEB-INF/common/vendor/");
    }
}
