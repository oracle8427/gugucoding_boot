package com.example.gugucoding_boot.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guestbook extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false)
	private String title;

	@Column(length = 1500, nullable = false)
	private String content;

	@Column(length = 50, nullable = false)
	private String writer;

	/*
	 * Entity 클래스는 가능하면 setter 관련 기능을 만들지 않는 것이 권장사항이지만
	 * 필요에 따라서는 수정기능을 만들기도 한다.
	 * 엔티티 객체가 애플리케이션 내부에서 변경되면 JPA 를 관리하는 쪽이 복잡해질 우려가 있기 때문에
	 * 가능하면 최소한의 수정이 가능하도록 처리
	 * */

	// 방명록의 제목을 수정한다.
	public void changeTitle(String title) {
		this.title = title;
	}

	// 방명록의 내용을 수정한다.
	public void changeContent(String content) {
		this.content = content;
	}

}
