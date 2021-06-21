package com.example.gugucoding_boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("")
	public String layout() {
		log.info("called layout...");
		return "layout";
	}

}
