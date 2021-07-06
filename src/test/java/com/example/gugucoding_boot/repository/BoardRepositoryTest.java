package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.entity.Board;
import com.example.gugucoding_boot.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;

	// @Test
	public void insertBoardTest() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Member member = Member.builder()
					.email("member" + i + "@email.com")
					.build();
			boardRepository.save(Board.builder()
					.title("Title..." + i)
					.content("Content..." + i)
					.writer(member)
					.build());
		});
	}

	@Test
	@Transactional
	public void readBoardTest() {
		Optional<Board> boardOptional= boardRepository.findById(100L);
		boardOptional.ifPresent(board -> {
			System.out.println(board);
			System.out.println(board.getWriter());
		});
	}

}
