package com.taez.shorturl.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;

public class TShortUrlUtil {

	private final String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final int LENGTH = CHARACTERS.length();
	
	private long indexGenerator() {
		long index = 0L;
		index = ThreadLocalRandom.current().nextLong(3521614606208L, 218340105584895L);
		return index;
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
	
	/**
	 * Decodes Base62 string to long number.
	 * 
	 * @param base62String
	 *            Base62 encoded string
	 * @return decoded long number
	 */
	public long decode(String base62String) {
		long base62Code = 0L;

		for (int i = 0; i < base62String.length(); ++i) {
			base62Code += CHARACTERS.indexOf(base62String.charAt(i))
					* Math.pow(LENGTH, (base62String.length() - 1) - i);
		}

		return base62Code;
	}

	/**
	 * Encodes long number to Base62 string.
	 * 
	 * @param number
	 *            long number
	 * @return encoded Base62 string
	 */
	public String encode(long number) {
		StringBuilder sb = new StringBuilder();
		do {
			sb.append(CHARACTERS.charAt((int) (number % LENGTH)));
			number /= LENGTH;
		} while (number != 0);

		return sb.reverse().toString();
	}
	
}
