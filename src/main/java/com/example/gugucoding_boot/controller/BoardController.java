package com.example.gugucoding_boot.controller;

import com.example.gugucoding_boot.dto.BoardDTO;
import com.example.gugucoding_boot.dto.PageRequestDTO;
import com.example.gugucoding_boot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final BoardService boardService;

	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		model.addAttribute("pageResult", boardService.pager(pageRequestDTO));
	}

	@GetMapping("/registration")
	public void registration() {
		log.info("[GET] Registration");
	}

	@PostMapping("/registration")
	public String registrationPost(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
		log.info("BoardDTO: " + boardDTO);

		Long id = boardService.registration(boardDTO);
		redirectAttributes.addFlashAttribute("registrationResult", id);
		return "redirect:/board/list";
	}


}
