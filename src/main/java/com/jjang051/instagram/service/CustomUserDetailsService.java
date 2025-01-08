package com.jjang051.instagram.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    Optional<Member> optionalMember = memberRepository.findByUserId(userId);
    if(optionalMember.isPresent()) {
      return new CustomUserDetails(optionalMember.get());
    }
    throw new UsernameNotFoundException("아이디 비번 확인해주세요.");
  } 
  
}
