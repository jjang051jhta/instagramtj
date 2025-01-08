package com.jjang051.instagram.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jjang051.instagram.constant.Role;
import com.jjang051.instagram.dto.JoinDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements IMemberService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final MemberRepository memberRepository;

  @Override
  public Member join(JoinDto joinDto) {
    Member saveMember = Member.builder()
      .userId(joinDto.getUserId())
      .userName(joinDto.getUserName())
      .userEmail(joinDto.getUserEmail())
      .role(Role.ROLE_USER)
      .password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
    .build();
    return memberRepository.save(saveMember);
  }
}
