package com.example.gugucoding_boot.repository.sample;

import com.example.gugucoding_boot.entity.sample.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

	List<Memo> findByIdBetweenOrderByIdDesc(Long from, Long to);

	Page<Memo> findByIdBetween(Long from, Long to, Pageable pageable);

	@Query("select m from Memo m ORDER BY m.id DESC")
	List<Memo> getListDESC();

	@Transactional
	@Modifying
	@Query("update Memo set memoText = :memoText where id = :id")
	int updateMemoText(@Param("id") long id, @Param("memoText") String memoText);

	@Transactional
	@Modifying
	@Query("update Memo set memoText = :#{#memo.memoText} where id = :#{#memo.id}")
	int updateMemoText2(@Param("memo") Memo memo);

	@Query(value = "select m from Memo m WHERE m.id > :id",
			countQuery = "select count(m) from Memo m WHERE m.id > :id")
	Page<Memo> getListWithQuery(long id, Pageable pageable);
}
