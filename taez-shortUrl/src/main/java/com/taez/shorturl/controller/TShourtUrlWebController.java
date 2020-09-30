package com.taez.shorturl.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taez.shorturl.service.TShortUrlService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TShourtUrlWebController {

	@Autowired
	TShortUrlService shortUrlService;
	
	@GetMapping("/web/home")
	public String homepage() {
		return "home.html";
	}
	
	@GetMapping("favicon.ico")
	@ResponseBody
	void returnNofavicon() {
	}
	
	private final String prefix = "http://";
	
	@ResponseBody
	@RequestMapping(value="/taez/shortUrl/save" , method= {RequestMethod.POST, RequestMethod.GET})
	public String getShortUrl(@RequestParam("url") String url, HttpServletResponse resp) throws UnsupportedEncodingException {
		String shortUrlKey = null;
		String errorMsg = null;
		
		//공백 제거 
		url = url.trim();
		url = URLDecoder.decode(url,"UTF-8");
		if(StringUtils.isEmpty(url)) {
			errorMsg = url + "is null";
			return "forward:/taez/error?errorMsg=" + errorMsg;
		}
		if(!(url.startsWith("http") || url.startsWith("https"))) {
			url = prefix.concat(url);
		}
		
		if(shortUrlService.isUrlable(shortUrlKey)) {
			errorMsg = url + "is already reistered";
			return "forward:/taez/error?errorMsg=" + errorMsg;
		}
		shortUrlKey = shortUrlService.gerShortUrlKey(url);
		shortUrlService.insertShortUrlKey(shortUrlKey, url);
		
		return shortUrlKey;
	}
	
	@ResponseBody
	@RequestMapping("/taez/error")
	public String errorProcess(@RequestParam("errorMsg") String errorMsg, HttpServletResponse res) {
		log.error("shortUrl api Err : {}", errorMsg);
		return errorMsg;
	}
	
}
