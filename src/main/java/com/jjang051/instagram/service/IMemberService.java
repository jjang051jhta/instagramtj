package com.jjang051.instagram.service;

import org.springframework.web.multipart.MultipartFile;

import com.jjang051.instagram.dto.JoinDto;
import com.jjang051.instagram.dto.ModifyDto;
import com.jjang051.instagram.entity.Member;

public interface IMemberService {
  public Member join(JoinDto joinDto);
  public Member changeProfile(String id, MultipartFile profileImage);
  public Member modifyMember(ModifyDto modifyDto);
}
