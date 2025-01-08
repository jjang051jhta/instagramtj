package com.jjang051.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjang051.instagram.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

}
