package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.dto.GuestbookDTO;
import com.example.gugucoding_boot.dto.PageRequestDTO;
import com.example.gugucoding_boot.dto.PageResultDTO;
import com.example.gugucoding_boot.entity.Guestbook;
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
				.pageNumber(1)
				.pageSize(10)
				.build();

		PageResultDTO<GuestbookDTO, Guestbook> pageResultDTO = guestbookService.getList(pageRequestDTO);
		log.info("PrevPage: " + pageResultDTO.isPrevPage());
		log.info("NextPage: " + pageResultDTO.isNextPage());
		log.info("TotalPage: " + pageResultDTO.getTotalPage());
		log.info("=============================================");
		pageResultDTO.getDtoList().forEach(guestbookDTO -> log.info(guestbookDTO.toString()));
		log.info("=============================================");
		pageResultDTO.getPageList().forEach(i -> log.info(String.valueOf(i)));
	}


}
