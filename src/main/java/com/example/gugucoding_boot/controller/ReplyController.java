package com.example.gugucoding_boot.controller;

import com.example.gugucoding_boot.dto.ReplyDTO;
import com.example.gugucoding_boot.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/replies/")
@RequiredArgsConstructor
public class ReplyController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final ReplyService replyService;

	@GetMapping(value = "/board/{boardId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("boardId") Long boardId) {
		log.info("getListByBoard(boardId: " + boardId + ")");
		return new ResponseEntity<>(replyService.getList(boardId), HttpStatus.OK);
	}


}
