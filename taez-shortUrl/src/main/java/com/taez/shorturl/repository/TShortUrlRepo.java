package com.taez.shorturl.repository;

public interface TShortUrlRepo {

	public boolean isContainKey(String shortUrlKey);
	
	public boolean isContainValue(String url);
	
	/**
	 * 현재 저장소가 빈값인지 확인 
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * 현재 등록된 총 로우수를 확인 
	 * @return
	 */
	public int getSize();

	/**
	 * 신규 ShortKey 등록 
	 * @param shortKey
	 * @param oriUrl
	 */
	public void insertKey(String shortUrlKey, String url);
	
	/**
	 * 기존 ShortKey 변경 
	 * @param shortKey
	 * @param oriUrl
	 */
	public void updateKey(String shortUrlKey, String url);

	/**
	 * ShortKey 에 해당하는 Url 을 가져온다 
	 * @param shortKey
	 * @return
	 */
	public String getOriUrl(String shortUrlKey);
	
	/**
	 * ShortKey 에 해당하는 Url 을 가져온다 
	 * @param shortKey
	 * @return
	 */
	public String getShortUrlKey(String url);

	/**
	 * 해당 shortKey 삭제 
	 * @param shortKey
	 * @return
	 */
	public boolean removeKey(String shortUrlKey);

}
