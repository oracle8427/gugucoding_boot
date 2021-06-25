package com.example.gugucoding_boot.service;

import com.example.gugucoding_boot.dto.GuestbookDTO;
import com.example.gugucoding_boot.dto.PageRequestDTO;
import com.example.gugucoding_boot.dto.PageResultDTO;
import com.example.gugucoding_boot.entity.Guestbook;

public interface GuestbookService {

	default Guestbook dtoToEntity(GuestbookDTO guestbookDTO) {
		return Guestbook.builder()
				.id(guestbookDTO.getId())
				.title(guestbookDTO.getTitle())
				.content(guestbookDTO.getContent())
				.writer(guestbookDTO.getWriter())
				.build();
	}

	default GuestbookDTO entityToDTO(Guestbook guestbook) {
		return GuestbookDTO.builder()
				.id(guestbook.getId())
				.title(guestbook.getTitle())
				.content(guestbook.getContent())
				.writer(guestbook.getWriter())
				.createdDate(guestbook.getCreatedDate())
				.lastModifiedDate(guestbook.getLastModifiedDate())
				.build();
	}

	Long registration(GuestbookDTO guestbookDTO);

	PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO);

}
