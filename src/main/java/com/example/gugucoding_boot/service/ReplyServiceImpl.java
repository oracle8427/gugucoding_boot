package com.example.gugucoding_boot.service;

import com.example.gugucoding_boot.dto.ReplyDTO;
import com.example.gugucoding_boot.entity.Board;
import com.example.gugucoding_boot.entity.Reply;
import com.example.gugucoding_boot.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	private final ReplyRepository replyRepository;

	@Override
	public Long registration(ReplyDTO replyDTO) {
		return replyRepository.save(dtoToEntity(replyDTO)).getId();
	}

	@Override
	public List<ReplyDTO> getList(Long boardId) {
		List<Reply> replyList = replyRepository.getRepliesByBoardOrderById(Board.builder().id(boardId).build());
		return replyList.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	@Override
	public void modify(ReplyDTO replyDTO) {
		replyRepository.save(dtoToEntity(replyDTO));
	}

	@Override
	public void remove(Long id) {
		replyRepository.deleteById(id);
	}
}
