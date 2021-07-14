package com.example.gugucoding_boot.repository.search;

import com.example.gugucoding_boot.entity.Board;
import com.example.gugucoding_boot.entity.QBoard;
import com.example.gugucoding_boot.entity.QMember;
import com.example.gugucoding_boot.entity.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public SearchBoardRepositoryImpl() {
		super(Board.class);
	}

	// test
	@Override
	public Board search() {
		log.info("search()...");
		QBoard board = QBoard.board;
		QMember member = QMember.member;
		QReply reply = QReply.reply;
		JPQLQuery<Board> jpqlQuery = from(board);
		jpqlQuery.leftJoin(member).on(board.writer.eq(member));
		jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

		JPQLQuery<Tuple> tupleJPQLQuery = jpqlQuery.select(board, member.email, reply.count());
		tupleJPQLQuery.groupBy(board);
		log.info("-----------------------------");
		log.info(tupleJPQLQuery.toString());
		log.info("-----------------------------");
		List<Board> result = jpqlQuery.fetch();
		return null;
	}

	@Override
	public Page<Object[]> searchPage(String searchType, String searchKeyword, Pageable pageable) {
		log.info("searchPage()...");
		QBoard board = QBoard.board;
		QMember member = QMember.member;
		QReply reply = QReply.reply;

		JPQLQuery<Board> jpqlQuery = from(board);
		jpqlQuery.leftJoin(member).on(board.writer.eq(member));
		jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

		JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count());

		BooleanBuilder booleanBuilder = new BooleanBuilder();
		BooleanExpression booleanExpression = board.id.gt(0L);
		booleanBuilder.and(booleanExpression);
		if (searchType != null && searchType.length() > 0 &&
				searchKeyword != null && searchKeyword.length() > 0) {
			String[] typeArray = searchType.split("");
			BooleanBuilder conditionBuilder = new BooleanBuilder();
			for (String type : typeArray) {
				switch (type) {
					case "t":
						conditionBuilder.or(board.title.contains(searchKeyword));
						break;
					case "w":
						conditionBuilder.or(member.email.contains(searchKeyword));
						break;
					case "c":
						conditionBuilder.or(board.content.contains(searchKeyword));
						break;
				}
			}
			booleanBuilder.and(conditionBuilder);
		}
		tuple.where(booleanBuilder);

		// order by
		Sort sort = pageable.getSort();
		sort.stream().forEach(order -> {
			Order direction = order.isAscending() ? Order.ASC : Order.DESC;
			String property = order.getProperty();
			PathBuilder<Board> orderByExpression = new PathBuilder<>(Board.class, "board");
			tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(property)));
		});
		tuple.groupBy(board);

		// page 처리
		tuple.offset(pageable.getOffset());
		tuple.limit(pageable.getPageSize());

		List<Tuple> result = tuple.fetch();
		log.info(result.toString());
		long count = tuple.fetchCount();
		return new PageImpl<>(
				result.stream().map(Tuple::toArray).collect(Collectors.toList()), pageable, count);
	}
}
