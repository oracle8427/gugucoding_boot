package com.example.gugucoding_boot.controller;

import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("guestbook")
public class GuestbookController {

	final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping({"/", "/list"})
	public String list() {
		return "guestbook/list";
	}

}
