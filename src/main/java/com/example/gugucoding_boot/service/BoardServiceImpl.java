package com.example.gugucoding_boot.service;

import com.example.gugucoding_boot.dto.BoardDTO;
import com.example.gugucoding_boot.dto.PageRequestDTO;
import com.example.gugucoding_boot.dto.PageResultDTO;
import com.example.gugucoding_boot.entity.Board;
import com.example.gugucoding_boot.entity.Member;
import com.example.gugucoding_boot.repository.BoardRepository;
import com.example.gugucoding_boot.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final BoardRepository boardRepository;

	private final ReplyRepository replyRepository;

	@Override
	public Long registration(BoardDTO boardDTO) {
		log.info("BoardDTO: " + boardDTO);
		Board board = dtoToEntity(boardDTO);
		boardRepository.save(board);
		return board.getId();
	}

	@Override
	public PageResultDTO<BoardDTO, Object[]> pager(PageRequestDTO pageRequestDTO) {
		log.info("PageRequestDTO: " + pageRequestDTO);
		Function<Object[], BoardDTO> function = (entity -> entityToDTO(
				(Board) entity[0],
				(Member) entity[1],
				(Long) entity[2]));
		Page<Object[]> pageResult = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("id").descending()));
		return new PageResultDTO<>(pageResult, function);
	}

	@Override
	public BoardDTO get(Long id) {
		Object result = boardRepository.getBoardById(id);
		if (result == null)
			return null;

		Object[] array = (Object[]) result;
		if (array.length < 3)
			return null;

		return entityToDTO(
				(Board) array[0],
				(Member) array[1],
				(Long) array[2]);
	}

	@Transactional
	@Override
	public void removeWithReplies(Long id) {
		replyRepository.deleteReplyById(id);
		boardRepository.deleteById(id);
	}


	@Override
	public void modify(BoardDTO boardDTO) {
		boardRepository.findById(boardDTO.getId()).ifPresent(board -> {
			board.changeTitle(boardDTO.getTitle());
			board.changeContent(boardDTO.getContent());
			boardRepository.save(board);
		});
	}


}
