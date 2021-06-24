package com.example.gugucoding_boot.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
 * @MappedSuperclass :
 * 	- 이 애너테이션이 적용된 클래스는 테이블로 생성되지 않는다.
 *  - 이 클래스를 상속한 엔티티의 클래스로 테이블이 생성된다.
 *
 * @EntityListeners :
 * 	- MyBatis 에서 SQL 을 위해 전달하는 객체는 모두 SQL 처리가 끝난 후, 어떻게 되던 상관이 없다.
 *  - JPA 에서 사용하는 엔티티 객체들은 영속성 컨텍스트 (Persistence Context) 에서 관리되는 객체다.
 *  - 이 객체들이 변경될 경우, 결과적으로 데이터 베이스에 이를 반영하는 방식.
 *  - 이처럼 필요할 때 꺼내서 재사용하는 방식에는 객체의 [변화]가 일어나는 것을 감지하는 리스너가 필요하다.
 *  - 이 리스너가 바로 AuditingEntityListener 클래스
 *  - 이 리스너를 활성화 시키키 위해서는 @EnableJpaAuditing 애너테이션의 추가가 필요하다.
 *    일반적으로 메인애플리케이션 클래스에 추가하는 것 같다.
 *
 * @CreatedDate
 * 	- 엔티티의 생성시간을 처리
 *  - updatable = false 속성 값을 지정하여, 이 엔티티 객체를 데이터베이스에 반영할 때,
 *    created_date 컬럼 값은 변경되지 않도록 처리
 *
 * @LastModifiedDate
 * 	- 최종 수정시간을 자동으로 처리
 **/

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract class BaseEntity {

	@CreatedDate
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

	@LastModifiedDate
	@Column(name="last_modified_date")
	private LocalDateTime lastModifiedDate;

}
