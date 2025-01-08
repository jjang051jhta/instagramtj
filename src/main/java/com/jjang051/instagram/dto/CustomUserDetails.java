package com.jjang051.instagram.dto;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jjang051.instagram.entity.Member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class CustomUserDetails implements UserDetails {

  private final Member loggedMember;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
  }

  @Override
  public String getPassword() {
    return loggedMember.getPassword();
  }

  @Override
  public String getUsername() {
    return loggedMember.getUserId();
  }
}
