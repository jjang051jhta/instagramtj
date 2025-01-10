package com.jjang051.instagram.social;

import java.util.Map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GoogleUserInfo implements SocialUserInfo {

  private final Map<String,Object> attributes;

  @Override
  public String getEmail() {
    return (String)attributes.get("email");
  }

  @Override
  public String getName() {
    return (String)attributes.get("name");
  }

  @Override
  public String getProvider() {
    return "google";
  }

  @Override
  public String getProviderId() {
    return getProvider()+"_"+attributes.get("sub");
  }

}
