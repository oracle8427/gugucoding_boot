package com.example.gugucoding_boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReplyServiceTest {

	@Autowired
	private ReplyService replyService;

	@Test
	public void getListTest() {
		Long boardId = 100L; // 데이터 베이스에 존재해야 한다.
		replyService.getList(boardId).forEach(System.out::println);
	}
}
