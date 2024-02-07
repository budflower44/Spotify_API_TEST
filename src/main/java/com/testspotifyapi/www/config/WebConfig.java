package com.testspotifyapi.www.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class [] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class [] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// 처음에 열렸을 때 기본 경로 -> index
		return new String [] {"/"};
	}
	
	//Encoding Filter 설정
	@Override
	protected Filter[] getServletFilters() {
		//filter 설정
		CharacterEncodingFilter encoding = new CharacterEncodingFilter();
		encoding.setEncoding("UTF-8");
		//외부로 나가는 데이터 인코딩 설정
		encoding.setForceEncoding(true);
		return new Filter[] {encoding};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		// 그 외 기타 사용자 설정
		// multipartConfig 설정
		// 사용자 지정 익셉션 처리 지정
		// -> 404와 같은 익셉션 처리 페이지 (꾸미기) -> 나중에 설정
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}
	
	
	
}
