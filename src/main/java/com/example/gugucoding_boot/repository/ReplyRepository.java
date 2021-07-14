package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.entity.Board;
import com.example.gugucoding_boot.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	@Modifying
	@Query(" DELETE FROM Reply r WHERE r.board.id =:id")
	void deleteReplyById(Long id);

	// 게시물로 댓글 목록 가져오기
	List<Reply> getRepliesByBoardOrderById(Board board);

}
