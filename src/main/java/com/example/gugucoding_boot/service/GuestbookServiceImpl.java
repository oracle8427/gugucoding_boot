package com.example.gugucoding_boot.service;

import com.example.gugucoding_boot.dto.GuestbookDTO;
import com.example.gugucoding_boot.dto.PageRequestDTO;
import com.example.gugucoding_boot.dto.PageResultDTO;
import com.example.gugucoding_boot.entity.Guestbook;
import com.example.gugucoding_boot.repository.GuestbookRepository;
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
		Page<Guestbook> pageResult = guestbookRepository.findAll(pageable);
		Function<Guestbook, GuestbookDTO> function = this::entityToDTO;
		return new PageResultDTO<>(pageResult, function);
	}

	@Override
	public GuestbookDTO read(Long id) {
		return guestbookRepository.findById(id).map(this::entityToDTO).orElse(null);
	}


}
