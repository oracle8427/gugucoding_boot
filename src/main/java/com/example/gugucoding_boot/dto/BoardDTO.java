package com.example.gugucoding_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

	private Long id;

	private String title;

	private String content;

	private String writerEmail; // 작성자 ID (email)

	private String writerName; // 작성자

	private LocalDateTime createdDate;

	private LocalDateTime lastModifiedDate;

	private int replyCount; // 게시글의 댓글 수

}
