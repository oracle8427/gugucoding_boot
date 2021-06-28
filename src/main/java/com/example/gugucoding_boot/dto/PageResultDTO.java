package com.example.gugucoding_boot.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, Entity> {

	// 데이터
	private List<DTO> dtoList;

	// 총 페이지 번호
	private int totalPage;

	// 현재 페이지 번호
	private int pageNumber;

	// 목록 사이즈
	private int pageSize;

	// 페이지 내 한번에 보여줄 개수
	private int perPage = 10;

	// 페이지 시작, 끝 번호
	private int startPage, endPage;

	// 이전, 다음
	private boolean prevPage, nextPage;

	// 페이지 번호 목록
	private List<Integer> pageList;

	public PageResultDTO(Page<Entity> pageResult, Function<Entity, DTO> function) {
		dtoList = pageResult.stream().map(function).collect(Collectors.toList());

		totalPage = pageResult.getTotalPages();
		makePageList(pageResult.getPageable());
	}

	private void makePageList(Pageable pageable) {
		this.pageNumber = pageable.getPageNumber() + 1; // JPA 는 0부터 시작하므로 + 1
		this.pageSize = pageable.getPageSize();

		int endPage = (int)(Math.ceil(pageNumber / 10.0)) * 10;
		this.startPage = endPage - (perPage - 1);
		this.prevPage = startPage > 1;

		this.endPage = Math.min(totalPage, endPage);
		this.nextPage = totalPage > endPage;

		this.pageList = IntStream.rangeClosed(startPage, endPage).boxed().collect(Collectors.toList());
	}

}
