package com.taez.shorturl.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

public class TShorUrlServiceTest {

	@Test
	public void getUrlByMD5Test() {
		String url = "http://naber.com";
		
		try {
			System.out.println(getUrlByMD5(url));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public String getUrlByMD5(String longUrl) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(longUrl.getBytes());
		byte[] arrBuf = md5.digest();
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arrBuf.length; i++) {
			sb.append(String.format("%02x", 0xFF & arrBuf[i]));
		}
		return sb.toString();
	}
	
}
