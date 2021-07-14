package com.example.gugucoding_boot.service;

import com.example.gugucoding_boot.dto.ReplyDTO;
import com.example.gugucoding_boot.entity.Board;
import com.example.gugucoding_boot.entity.Reply;

import java.util.List;

public interface ReplyService {

	Long registration(ReplyDTO replyDTO);

	List<ReplyDTO> getList(Long id);

	void modify(ReplyDTO replyDTO);

	void remove(Long id);

	// ReplyDTO 를 Reply 객체로 변환 Board 객체의 처리가 수반됨
	default Reply dtoToEntity(ReplyDTO replyDTO) {
		Board board = Board.builder().id(replyDTO.getBoardId()).build();
		return Reply.builder()
				.id(replyDTO.getId())
				.text(replyDTO.getText())
				.replier(replyDTO.getReplier())
				.board(board)
				.build();
	}

	// Reply 객체를 ReplyDTO 로 변환. Board 객체가 필요하지 않으므로 게시물 번호만
	default ReplyDTO entityToDTO(Reply reply) {
		return ReplyDTO.builder()
				.id(reply.getId())
				.text(reply.getText())
				.replier(reply.getReplier())
				.createdDate(reply.getCreatedDate())
				.lastModifiedDate(reply.getLastModifiedDate())
				.build();
	}
}
