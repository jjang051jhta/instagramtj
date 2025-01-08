package com.jjang051.instagram.service;

import com.jjang051.instagram.dto.JoinDto;
import com.jjang051.instagram.entity.Member;

public interface IMemberService {
  public Member join(JoinDto joinDto);
}
