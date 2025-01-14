package com.jjang051.instagram.dto;

import com.jjang051.instagram.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberInfoDto {
  private boolean pageOwner;
  private Member member;
  private int subscribeCount;
  private boolean subscribeState;
  private int imageTotal;
  
  
  //구독자수
  //몇개의 이미지를 업로드 했는지
}
