package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.dto.PageRequestDTO;
import com.example.gugucoding_boot.service.GuestbookService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTest {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private GuestbookService guestbookService;

	@Test
	public void getListTest() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
				.page(1)
				.size(10)
				.build();

		guestbookService.getList(pageRequestDTO).getDtoList().forEach(guestbookDTO -> log.info(guestbookDTO.toString()));

	}


}
