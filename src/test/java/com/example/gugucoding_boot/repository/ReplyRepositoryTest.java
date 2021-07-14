package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.entity.Board;
import com.example.gugucoding_boot.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

@SpringBootTest
public class ReplyRepositoryTest {

	@Autowired
	private ReplyRepository replyRepository;

	// @Test
	public void insertReplyTest() {
		LongStream.rangeClosed(1, 300).forEach(l -> {
			long id = (long)(Math.random() * 100) + 1;
			Board board = Board.builder()
					.id(id)
					.build();
			replyRepository.save(Reply.builder()
					.text("Reply text..." + l)
					.replier("guest")
					.board(board)
					.build());
		});
	}

	@Test
	public void readReply() {
		Optional<Reply> replyOptional = replyRepository.findById(1L);
		replyOptional.ifPresent(reply -> {
			Board board = reply.getBoard();
			System.out.println(board.getWriter());
		});
	}

	@Test
	public void listByBoardTest() {
		List<Reply> replyList= replyRepository.getRepliesByBoardOrderById(Board.builder().id(97L).build());
		replyList.forEach(System.out::println);
	}


}
