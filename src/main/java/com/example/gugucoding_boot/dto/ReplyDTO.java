package com.example.gugucoding_boot.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ReplyDTO {

	private Long id;

	private String text;

	private String replier;

	private Long boardId;

	private LocalDateTime createdDate, lastModifiedDate;

}
