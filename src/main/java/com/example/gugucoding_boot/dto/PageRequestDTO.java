package com.example.gugucoding_boot.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

	private int pageNumber;
	private int pageSize;
	private String searchType;
	private String searchKeyword;

	public PageRequestDTO() {
		this.pageNumber = 1;
		this.pageSize = 10;
	}

	public Pageable getPageable(Sort sort) {
		return PageRequest.of(pageNumber - 1, pageSize, sort);
	}
}
