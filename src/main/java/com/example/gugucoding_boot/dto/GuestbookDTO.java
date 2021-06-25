package com.example.gugucoding_boot.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/*
 * Data Transfer Object
 * [목적]
 * 전달 목적
 * 1회성
 * RW
 *
 * [단점]
 * Entity 클래스와 유사한 코드가 중복으로 개발된다.
 * Entity 객체를 DTO 객체로 변환, DTO 객체를 Entity 객체로 변환하는 과정이 필요.
 * ModelMapper 라이브러리 (http://modelmapper.org)
 * MapStruct 라이브러리 (https://mapstruct.org) 등을 이용하기도 한다.
 * */
@Builder
@Data
public class GuestbookDTO {
	private Long id;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime createdDate, lastModifiedDate;
}
