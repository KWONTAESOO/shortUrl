package com.taez.shorturl.service;

public interface TShortUrlService {

	/**
	 * 해당 url로 저장된 shortUrlKey를 찾음 
	 * @param url
	 * @return
	 */
	public String gerShortUrlKey(String url);
	
	/**
	 * 생성된 shortUrlKey 와 url을 조합하여 저장한다.
	 * @param shortUrlKey
	 * @param url
	 * @return
	 */
	public boolean insertShortUrlKey(String shortUrlKey, String url);
	
	/**
	 * short url key 를 통해서 기존 저장된 URL 을 가져옴 
	 * @param shortUrlKey
	 * @return
	 */
	public String getOriUrl(String shortUrlKey);
	
	/**
	 * 해당 shortUrlKey 가 저장되어 있는지 확인한다.
	 * @param shortUrlKey
	 * @return
	 */
	public boolean isShortUrlKeyable(String shortUrlKey);
	
	/**
	 * 해당 url 이 저장되어 있는지 확인한다.
	 * @param url
	 * @return
	 */
	public boolean isUrlable(String url);
}
