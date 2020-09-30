package com.taez.shorturl.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taez.shorturl.repository.TShortUrlRepo;
import com.taez.shorturl.service.TShortUrlService;
import com.taez.shorturl.util.ShortUrlUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * shortUrlKey 를 체크하여 redirect 시켜주는 로직 
 * @author taez
 *
 */
@Slf4j
@Controller
public class TShortUrlController {

	@Autowired
	TShortUrlService shortUrlService;

	@RequestMapping("/taez/link")
	public String gerUrl(@RequestParam("shortUrlKey") String shortUrlKey, HttpServletResponse res) {
		String longURL = shortUrlService.getOriUrl(shortUrlKey);
		return "redirect:" + longURL;
	}

	@RequestMapping(value = { "/{shortUrlKey}" })
	public String home(@PathVariable("shortUrlKey") String shortUrlKey, HttpServletResponse resp) throws IOException {
		log.info("shortUrlKey : " + shortUrlKey);
		// validation Check
		String errorMsg = null;
		if (shortUrlKey.length() != 8)
			errorMsg = "shortUrlKey size is wrong";
		// shortUrlKey 가 존제하는지 파악한다 
		if (!shortUrlService.isShortUrlKeyable(shortUrlKey))
			errorMsg = "shortUrlKey is wrong";
		
		if(!StringUtils.isEmpty(errorMsg))
			return "forward:/taez/error?errorMsg=" + errorMsg;
			
		return "forward:/taez/link?shortUrlKey=" + shortUrlKey;
	}
	
	

}
