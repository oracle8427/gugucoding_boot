package com.example.gugucoding_boot.repository.search;

import com.example.gugucoding_boot.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {

	Board search();

	// Pageable 파라미터를 전달하여, Page<Object[]>를 생성하고 반환
	// 검색 타입, 검색어어
	Page<Object[]> searchPage(String searchType, String searchKeyword, Pageable pageable);

}
