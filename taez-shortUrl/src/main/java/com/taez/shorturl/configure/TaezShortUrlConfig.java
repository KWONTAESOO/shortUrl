package com.taez.shorturl.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * short url 관련 설정 정보 
 * @author taez
 *
 */
@Setter
@Getter
@Configuration
public class TaezShortUrlConfig {
	
	/**
	 * ShortUrlKey 저장 공간 
	 */
	@Value("${taez.repository.size}")
	int repositorySize;
	
	/**
	 * ShortUrlKey 저장 타입 (DB/FILE/REDIS...)
	 */
	@Value("${taez.repository.type}")
	String repositoryType;
	
	@Value("${taez.encode.base62}")
	String encodeBase62;
	
}
