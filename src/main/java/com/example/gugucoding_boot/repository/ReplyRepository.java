package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	@Modifying
	@Query(" DELETE FROM Reply r WHERE r.board.id =:id")
	void deleteReplyById(Long id);

}
