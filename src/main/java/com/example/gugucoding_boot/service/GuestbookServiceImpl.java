package com.example.gugucoding_boot.service;

import com.example.gugucoding_boot.dto.GuestbookDTO;
import com.example.gugucoding_boot.dto.PageRequestDTO;
import com.example.gugucoding_boot.dto.PageResultDTO;
import com.example.gugucoding_boot.entity.Guestbook;
import com.example.gugucoding_boot.entity.QGuestbook;
import com.example.gugucoding_boot.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

	private final GuestbookRepository guestbookRepository;

	@Override
	public Long registration(GuestbookDTO guestbookDTO) {
		Guestbook guestbook = dtoToEntity(guestbookDTO);
		return guestbookRepository.save(guestbook).getId();
	}

	public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("id"));
		BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);
		Page<Guestbook> pageResult = guestbookRepository.findAll(booleanBuilder, pageable);
		Function<Guestbook, GuestbookDTO> function = this::entityToDTO;
		return new PageResultDTO<>(pageResult, function);
	}

	@Override
	public GuestbookDTO read(Long id) {
		return guestbookRepository.findById(id).map(this::entityToDTO).orElse(null);
	}

	@Override
	public void remove(Long id) {
		guestbookRepository.deleteById(id);
	}

	@Override
	public void modify(GuestbookDTO guestbookDTO) {
		guestbookRepository.findById(guestbookDTO.getId()).ifPresent(guestbook -> {
			guestbook.changeTitle(guestbookDTO.getTitle());
			guestbook.changeContent(guestbookDTO.getContent());
			guestbookRepository.save(guestbook);
		});
	}

	public BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QGuestbook qGuestbook = QGuestbook.guestbook;
		BooleanExpression booleanExpression = qGuestbook.id.gt(0L);
		booleanBuilder.and(booleanExpression);

		String searchType = pageRequestDTO.getSearchType();
		String searchKeyword = pageRequestDTO.getSearchKeyword();
		if (searchType == null || searchType.trim().length() == 0)
			return booleanBuilder;
		if (searchKeyword == null || searchKeyword.trim().length() == 0)
			return booleanBuilder;

		BooleanBuilder conditionBooleanBuilder = new BooleanBuilder();
		if (searchType.contains("t"))
			conditionBooleanBuilder.or(qGuestbook.title.contains(searchKeyword));
		if (searchType.contains("c"))
			conditionBooleanBuilder.or(qGuestbook.content.contains(searchKeyword));
		if (searchType.contains("w"))
			conditionBooleanBuilder.or(qGuestbook.writer.contains(searchKeyword));
		booleanBuilder.and(conditionBooleanBuilder);

		return booleanBuilder;
	}


}
