package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
