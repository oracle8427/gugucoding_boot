package com.example.gugucoding_boot.controller;

import com.example.gugucoding_boot.dto.PageRequestDTO;
import com.example.gugucoding_boot.dto.PageResultDTO;
import com.example.gugucoding_boot.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("guestbook")
@RequiredArgsConstructor
public class GuestbookController {

	final Logger log = LoggerFactory.getLogger(getClass());

	private final GuestbookService guestbookService;

	@GetMapping({"/", "/list"})
	public String list(PageRequestDTO pageRequestDTO, Model model) {
		model.addAttribute("pageResult", guestbookService.getList(pageRequestDTO));
		return "guestbook/list";
	}

}
