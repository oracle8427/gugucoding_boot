package com.example.gugucoding_boot.repository;

import com.example.gugucoding_boot.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
