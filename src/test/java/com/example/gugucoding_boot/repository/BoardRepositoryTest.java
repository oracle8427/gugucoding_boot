package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.entity.Board;
import com.example.gugucoding_boot.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
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
		Optional<Board> boardOptional = boardRepository.findById(100L);
		boardOptional.ifPresent(board -> {
			System.out.println(board);
			System.out.println(board.getWriter());
		});
	}

	@Test
	public void getBoardWithWriterTest() {
		Object result = boardRepository.getBoardWithWriter(100L);
		System.out.println(Arrays.toString((Object[]) result));
	}

	@Test
	public void getBoardWithReplyTest() {
		List<Object[]> result = boardRepository.getBoardWithReply(100L);
		for (Object[] arr : result) {
			System.out.println(Arrays.toString(arr));
		}
	}

	@Test
	public void getBoardWithReplyCountTest() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
		Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
		result.forEach(row -> System.out.println(Arrays.toString(row)));
	}

	@Test
	public void getBoardByIdTest() {
		Object result = boardRepository.getBoardById(100L);
		System.out.println(Arrays.toString((Object[]) result));
	}

	@Test
	public void searchTest() {
		boardRepository.search();
	}

	@Test
	public void searchPageTest() {
		Pageable pageable = PageRequest.of(
				0,
				10,
				Sort.by("id").descending().and(Sort.by("title").ascending()));
		Page<Object[]> pageResult = boardRepository.searchPage("t", "1", pageable);
	}

}
