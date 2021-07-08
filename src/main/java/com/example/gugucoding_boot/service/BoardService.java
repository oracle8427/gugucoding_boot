package com.example.gugucoding_boot.service;

import com.example.gugucoding_boot.dto.BoardDTO;
import com.example.gugucoding_boot.dto.PageRequestDTO;
import com.example.gugucoding_boot.dto.PageResultDTO;
import com.example.gugucoding_boot.entity.Board;
import com.example.gugucoding_boot.entity.Member;

public interface BoardService {

	Long registration(BoardDTO boardDTO);

	default Board dtoToEntity(BoardDTO boardDTO) {
		Member member = Member.builder()
				.email(boardDTO.getWriterEmail())
				.build();

		return Board.builder()
				.id(boardDTO.getId())
				.title(boardDTO.getTitle())
				.content(boardDTO.getContent())
				.writer(member)
				.build();
	}

	default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
		return BoardDTO.builder()
				.id(board.getId())
				.title(board.getTitle())
				.content(board.getContent())
				.createdDate(board.getCreatedDate())
				.lastModifiedDate(board.getLastModifiedDate())
				.writerEmail(member.getEmail())
				.writerName(member.getName())
				.replyCount(replyCount.intValue())
				.build();
	}

	PageResultDTO<BoardDTO, Object[]> pager(PageRequestDTO pageRequestDTO);

	BoardDTO get(Long id);

	void removeWithReplies(Long id);

	void modify(BoardDTO boardDTO);
}
