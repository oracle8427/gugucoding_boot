package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

	List<Memo> findByIdBetweenOrderByIdDesc(Long from, Long to);

	Page<Memo> findByIdBetween(Long from, Long to, Pageable pageable);

}
