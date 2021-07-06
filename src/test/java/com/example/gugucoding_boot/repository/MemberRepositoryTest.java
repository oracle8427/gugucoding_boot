package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	@Test
	public void insertMemberTest() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			memberRepository.save(Member.builder()
					.email("member" + i + "@email.com")
					.password("1234")
					.name("MemberName_" + i)
					.build());
		});
	}

}
