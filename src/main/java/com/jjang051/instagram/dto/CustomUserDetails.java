package com.jjang051.instagram.dto;


import java.util.ArrayList;
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
    Collection<GrantedAuthority> collection = new ArrayList<>();
    collection.add((GrantedAuthority)()-> String.valueOf(loggedMember.getRole()));
    return collection;
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
