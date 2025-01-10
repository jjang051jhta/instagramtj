package com.jjang051.instagram.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.jjang051.instagram.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2DetailsService extends DefaultOAuth2UserService {
  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    log.info("userRequest==={}",userRequest);

    Map<String,Object> oauth2UserInfo = (Map)oAuth2User.getAttributes();
    log.info("(Map)oAuth2User.getAttributes()==={}",oAuth2User.getAttributes());

		return null;
	}
}
