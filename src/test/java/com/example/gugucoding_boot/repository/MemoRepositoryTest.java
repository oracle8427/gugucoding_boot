package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.entity.sample.Memo;
import com.example.gugucoding_boot.repository.sample.MemoRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTest {

	@Autowired
	private MemoRepository memoRepository;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Test
	@Order(1)
	public void testInsert() {
		IntStream.rangeClosed(1, 50).forEach(i -> {
			Memo memo = memoRepository.save(Memo.builder().id(i).memoText("Memo..." + i).build());
			log.info(memo.toString());
		});
	}

	@Test
	@Order(2)
	public void testSelect() {
		long id = 1L;
		memoRepository.findById(id).ifPresent(memo -> log.info(memo.toString()));
	}

	@Test
	@Order(3)
	@Transactional
	public void testGetOne() throws InterruptedException {
		log.info("Call testGetOne { ");
		long id = 1L;
		Memo memo = memoRepository.getOne(id);
		log.info("} // testGetOne");
		Thread.sleep(2000);

		log.info(memo.toString());
	}

	@Test
	@Order(4)
	public void testPagination() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Memo> result = memoRepository.findAll(pageable);

		log.info("Total Pages: " + result.getTotalPages());
		log.info("Total Count: " + result.getTotalElements());
		log.info("Page Number: " + result.getNumber());
		log.info("Page Size: " + result.getSize());
		log.info("has next page? " + result.hasNext());
		log.info("first page? " + result.isFirst());

		result.forEach(memo -> log.info(memo.toString()));


		memoRepository.findAll(PageRequest.of(0, 10, Sort.by("id").descending()))
				.forEach(memo -> log.info(memo.toString()));


		// Sort sort = Sort.by("id").descending().and(Sort.by("memoText").ascending());
	}

	@Test
	@Order(5)
	public void testFindByIdBetweenOrderByIdDesc() {
		memoRepository.findByIdBetweenOrderByIdDesc(30L, 40L).forEach(memo -> log.info(memo.toString()));
	}

	@Test
	@Order(6)
	public void testFindByIdBetween() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
		memoRepository.findByIdBetween(10L, 30L, pageable).forEach(memo -> log.info(memo.toString()));
	}

	@Test
	@Order(7)
	public void testGetListDESC() {
		memoRepository.getListDESC().forEach(memo -> log.info(memo.toString()));
	}


	@Test
	@Order(8)
	public void testUpdateMemoText() {
		int affected = memoRepository.updateMemoText(2, "update memo...2");
		log.info("affected rows: " + affected);
	}

	@Test
	@Order(9)
	public void testUpdateMemoText2() {
		Memo memo = new Memo(2, "update memo...2");
		int affected = memoRepository.updateMemoText2(memo);
		log.info("affected rows: " + affected);
	}

	@Test
	@Order(10)
	public void testGetListWithQuery() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
		memoRepository.getListWithQuery(10L, pageable).forEach(memo -> log.info(memo.toString()));
	}
}
