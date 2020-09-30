package com.taez.shorturl.configure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.taez.shorturl.repository.TShortUrlRepo;
import com.taez.shorturl.repository.TShortUrlRepository;
import com.taez.shorturl.util.ShortUrlUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * short url 관련 bean
 * @author taez
 *
 */
@Slf4j
@Component
public class TaezShortUrl {
	
	@Autowired
	TaezShortUrlConfig configure;
	
	private String repositoryType = null; 
	
	/**
	 * shortUrlKey 를 저장항 repository 를 생성한다.
	 * 추후 DB/FILE/REDIS 등이 가능하도록 구성 
	 * @return
	 */
	@Bean
	public TShortUrlRepo createRepository() {
		this.repositoryType = configure.getRepositoryType();
		log.info("create repository ] type: {} , size: {}", repositoryType, configure.getRepositorySize());
		Map<String, String> map = new ConcurrentHashMap<String, String>(configure.getRepositorySize());
		TShortUrlRepo repository = new TShortUrlRepository(map);
		return repository;
	}
	
	@Bean
	public ShortUrlUtil createShortUrl() {
		ShortUrlUtil shorUrlUtil = new ShortUrlUtil(configure);
		return shorUrlUtil;
	}

}
