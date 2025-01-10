package com.jjang051.instagram.dto;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.jjang051.instagram.entity.Member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

//@RequiredArgsConstructor
@Getter
@ToString
public class CustomUserDetails implements UserDetails, OAuth2User {

  private final Member loggedMember;

  private Map<String,Object> attributes;

  public CustomUserDetails(Member loggedMember) {
    this.loggedMember=loggedMember;
  }

  public CustomUserDetails(Member loggedMember,Map<String,Object> attributes) {
    this.loggedMember=loggedMember;
    this.attributes = attributes;
  }
  

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

  @Override
  public String getName() {
    return (String)attributes.get("name");
  }


  @Override
  public Map<String,Object> getAttributes() {
    return attributes;
  }
}
