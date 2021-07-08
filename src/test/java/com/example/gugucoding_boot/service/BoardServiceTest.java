package com.example.gugucoding_boot.service;


import com.example.gugucoding_boot.dto.BoardDTO;
import com.example.gugucoding_boot.dto.PageRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTest {

	@Autowired
	private BoardService boardService;

	@Test
	public void registrationTest() {
		BoardDTO boardDTO = BoardDTO.builder()
				.title("Test title... Registration")
				.content("Test content... Registration")
				.writerEmail("member16@email.com")
				.build();

		boardService.registration(boardDTO);
	}

	@Test
	public void pagerTest() {
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		boardService.pager(pageRequestDTO).getDtoList().forEach(System.out::println);
	}

	@Test
	public void getTest() {
		Long id = 100L;
		System.out.println(boardService.get(id));
	}

	// @Test 데이터베이스에 있는 데이터를 매개변수로 전달해야 한다.
	public void removeWithRepliesTest() {
		boardService.removeWithReplies(2L);
	}

	@Test
	public void modifyTest() {
		BoardDTO boardDTO = BoardDTO.builder()
				.id(1L)
				.title("Change Title")
				.content("Change content")
				.build();
		boardService.modify(boardDTO);
	}

}
