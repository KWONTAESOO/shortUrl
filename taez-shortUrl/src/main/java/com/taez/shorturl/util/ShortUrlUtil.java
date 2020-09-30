package com.taez.shorturl.util;

import java.util.concurrent.ThreadLocalRandom;

import com.taez.shorturl.configure.TaezShortUrlConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShortUrlUtil {

	TaezShortUrlConfig configure;

	public ShortUrlUtil(TaezShortUrlConfig configure) {
		this.configure = configure;
	}

	/**
	 * shortUrlKey 를 생성하기 전 랜덤 숫자를 생성한다.
	 * 
	 * @retur radom long
	 */
	public long createRandomIdx() {
		long randomIdx = ThreadLocalRandom.current().nextLong(3521614606208L, 218340105584896L);
		log.debug("create randomIdx : {}", randomIdx);
		return randomIdx;
	}

	public String createShortKey(long decimal) {
		String result = (decimal == 0 ? "0" : "");
		int mod = 0;
		while (decimal != 0) {
			mod = (int) (decimal % configure.getEncodeBase62().length());
			result = configure.getEncodeBase62().substring(mod, mod + 1) + result;
			decimal /= configure.getEncodeBase62().length();
		}
		return result;
	}

}
