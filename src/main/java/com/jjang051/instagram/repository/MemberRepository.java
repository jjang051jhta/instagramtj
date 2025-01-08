package com.jjang051.instagram.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjang051.instagram.entity.Member;
import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
  Optional<Member> findByUserId(String userId);
}
