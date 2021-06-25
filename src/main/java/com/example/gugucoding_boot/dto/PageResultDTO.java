package com.example.gugucoding_boot.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResultDTO<DTO, Entity> {

	private List<DTO> dtoList;

	public PageResultDTO(Page<Entity> pageResult, Function<Entity, DTO> function) {
		dtoList = pageResult.stream().map(function).collect(Collectors.toList());
	}

}
