package com.taez.shorturl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taez.shorturl.repository.TShortUrlRepo;
import com.taez.shorturl.util.ShortUrlUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TShortUrlServiceImpl implements TShortUrlService{

	@Autowired
	TShortUrlRepo repository;
	
	@Autowired
	ShortUrlUtil shorUrlUtil;
	
	@Override
	public String gerShortUrlKey(String url) {
		String shortUrlKey = null;
		if(!repository.isContainValue(url)) {
			long decimal = shorUrlUtil.createRandomIdx();
			shortUrlKey = shorUrlUtil.createShortKey(decimal);
		}else {
			shortUrlKey = repository.getShortUrlKey(url);
		}
		return shortUrlKey;
	}
	
	@Override
	public boolean insertShortUrlKey(String shortUrlKey, String url) {
		boolean result = false;
		repository.insertKey(shortUrlKey, url);
		result = repository.isContainKey(shortUrlKey);
		return result;
	}
	
	@Override
	public String getOriUrl(String shortUrlKey) {
		return repository.getOriUrl(shortUrlKey);
	}

	@Override
	public boolean isShortUrlKeyable(String shortUrlKey) {
		return repository.isContainKey(shortUrlKey);
	}

	@Override
	public boolean isUrlable(String url) {
		return repository.isContainValue(url);
	}



}
