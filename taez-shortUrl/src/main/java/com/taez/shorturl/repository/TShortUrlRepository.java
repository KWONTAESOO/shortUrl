package com.taez.shorturl.repository;

import java.util.Map;
import java.util.Map.Entry;

public class TShortUrlRepository implements TShortUrlRepo {

	private Map<String, String> shortUrlRepository;

	public TShortUrlRepository(Map<String, String> map) {
		this.shortUrlRepository = map;
	}

	@Override
	public boolean isContainKey(String key) {
		return shortUrlRepository.containsKey(key);
	}
	
	@Override
	public boolean isContainValue(String value) {
		for(Entry<String,String> val : shortUrlRepository.entrySet()) {
			if(val.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public boolean isEmpty() {
		return shortUrlRepository.isEmpty();
	}

	@Override
	public int getSize() {
		return shortUrlRepository.size();
	}

	@Override
	public void insertKey(String shortKey, String oriUrl) {
		shortUrlRepository.put(shortKey, oriUrl);
	}

	@Override
	public void updateKey(String shortKey, String oriUrl) {
		shortUrlRepository.put(shortKey, oriUrl);
	}

	@Override
	public String getOriUrl(String shortKey) {
		return shortUrlRepository.get(shortKey);
	}

	@Override
	public boolean removeKey(String shortKey) {
		shortUrlRepository.remove(shortKey);
		return !shortUrlRepository.containsKey(shortKey);
	}

	@Override
	public String getShortUrlKey(String url) {
		for(Entry<String,String> val : shortUrlRepository.entrySet()) {
			if(val.getValue().equals(url)) {
				return val.getKey();
			}
		}
		return null;
	}

}
