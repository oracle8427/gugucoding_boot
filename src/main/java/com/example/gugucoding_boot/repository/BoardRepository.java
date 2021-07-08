package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

	@Query("SELECT b, w FROM Board b LEFT JOIN b.writer w WHERE b.id =:id")
	Object getBoardWithWriter(@Param("id") Long id);

	@Query("SELECT b, r FROM Board b LEFT JOIN Reply r ON r.board = b WHERE b.id =:id")
	List<Object[]> getBoardWithReply(@Param("id") Long id);

	@Query(value = "SELECT b, w, COUNT(r) " +
			" FROM Board  b" +
			" LEFT JOIN b.writer w " +
			" LEFT JOIN Reply r ON r.board = b " +
			" GROUP BY b",
			countQuery = "SELECT COUNT(b) FROM Board b"
	)
	Page<Object[]> getBoardWithReplyCount(Pageable pageable);

	@Query("SELECT b, w, COUNT(r)" +
			" FROM Board b LEFT JOIN b.writer w " +
			" LEFT OUTER JOIN Reply r ON r.board = b" +
			" WHERE b.id =:id")
	Object getBoardById(@Param("id") Long id);

}
