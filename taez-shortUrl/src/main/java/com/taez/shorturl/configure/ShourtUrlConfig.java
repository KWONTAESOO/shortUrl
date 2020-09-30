package com.taez.shorturl.configure;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ShourtUrlConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/web/*").addResourceLocations("classpath:/templates/")
				.setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
	}
	

}
