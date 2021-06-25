package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.entity.Guestbook;
import com.example.gugucoding_boot.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
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

import java.util.stream.IntStream;


@SpringBootTest
public class GuestbookRepositoryTest {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private GuestbookRepository guestbookRepository;



	@Test
	@Order(1)
	public void insertGuestbook() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			Guestbook guestbook = Guestbook.builder()
					.title("Title..." + i)
					.content("Content..." + i)
					.writer("user" + (i % 10))
					.build();
			log.info(guestbookRepository.save(guestbook).toString());
		});
	}

	@Test
	@Order(2)
	public void updateTest() {
		guestbookRepository.findById(1L).ifPresent(guestbook -> {
			guestbook.changeTitle("Changed Title...");
			guestbook.changeContent("Changed Content...");
			log.info(guestbookRepository.save(guestbook).toString());
		});
	}

	@Test
	public void queryTest() {

		Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));

		// Q 도메인 객체를 얻어온다. Q 도메인 객체의 필드를 변수로 사용할 수 있다.
		QGuestbook qGuestbook = QGuestbook.guestbook;
		String keyword = "1";

		// where 조건들을 넣어주는 컨테이너 ( and, or... 등등)
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		// 원하는 조건을 Q 도메인 객체의 필드값과 결합하여 사용한다.
		// BooleanBuilder 안에 들어가는 값은 com.querydsl.core.types.Predicate 타입이여야 한다. (Java API 아님)
		// GuestbookRepository 에 추가된 QuerydslPredicateExecutor 인터페이스의 findAll()을 사용할 수 있다.
		BooleanExpression booleanExpression  = qGuestbook.title.contains(keyword);
		booleanBuilder.and(booleanExpression);
		Page<Guestbook> pageGuestbook = guestbookRepository.findAll(booleanBuilder, pageable);
		pageGuestbook.forEach(guestbook -> log.info(guestbook.toString()));

	}

	@Test
	public void queryTest2() {

		Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
		QGuestbook qGuestbook = QGuestbook.guestbook;
		String keyword = "1";

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		// WHERE (title = '' or content = '')
		BooleanExpression titleExpression  = qGuestbook.title.contains(keyword);
		BooleanExpression contentExpression  = qGuestbook.content.contains(keyword);
		BooleanExpression booleanExpression = titleExpression.or(contentExpression);
		booleanBuilder.and(booleanExpression);
		booleanBuilder.and(qGuestbook.id.gt(1L));

		Page<Guestbook> pageGuestbook = guestbookRepository.findAll(booleanBuilder, pageable);
		pageGuestbook.forEach(guestbook -> log.info(guestbook.toString()));
	}


}
