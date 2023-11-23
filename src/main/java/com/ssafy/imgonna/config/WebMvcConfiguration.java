package com.ssafy.imgonna.config;

import com.ssafy.imgonna.common.interceptor.LoginInterceptor;
import com.ssafy.imgonna.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;


@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	private final Logger logger = LoggerFactory.getLogger(WebMvcConfiguration.class);

	private final String uploadFilePath;
	private final JWTUtil jwtUtil;

	public WebMvcConfiguration(@Value("${file.path}") String uploadFilePath, JWTUtil jwtUtil) {
		this.uploadFilePath = uploadFilePath;
		this.jwtUtil = jwtUtil;

	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
//			.allowedOrigins("http://localhost:8080", "http://localhost:8081")
				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
						HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
						HttpMethod.PATCH.name())
//				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
//				.allowedMethods("*")
				.maxAge(1800); // 1800초 동안 preflight 결과를 캐시에 저장
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 웹브라우저에 입력하는 url에 /upload 로 시작하는 경우 uploadPath에 설정한 폴더 기준으로 파일을 읽어오도록 설정
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///" + uploadFilePath + "/")
				.setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor(jwtUtil))
				.addPathPatterns("/imgonna/**");
//				.excludePathPatterns("/", "/members", "/members/login", "/members/logout/*");
	}

}
