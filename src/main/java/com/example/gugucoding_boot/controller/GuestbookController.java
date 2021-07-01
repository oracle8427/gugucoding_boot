package com.example.gugucoding_boot.controller;

import com.example.gugucoding_boot.dto.GuestbookDTO;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("/registration")
	public void registration() {
		log.info("[GET] /registration...");
	}

	@PostMapping("/registration")
	public String registrationPOST(GuestbookDTO guestbookDTO, RedirectAttributes redirectAttributes) {
		log.info("[GuestbookDTO] : " + guestbookDTO);
		Long id = guestbookService.registration(guestbookDTO);
		redirectAttributes.addFlashAttribute("registrationResult", id);
		return "redirect:/guestbook/list";
	}

	@GetMapping("/read")
	public void read(long id, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model) {
		log.info("[read]: " + id);
		model.addAttribute("guestbookDTO", guestbookService.read(id));
	}


}
