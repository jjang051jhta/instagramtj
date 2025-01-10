package com.jjang051.instagram.service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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

import com.jjang051.instagram.constant.Role;
import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.repository.MemberRepository;
import com.jjang051.instagram.social.GoogleUserInfo;
import com.jjang051.instagram.social.SocialUserInfo;

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
    log.info("userRequest==={}",userRequest.getClientRegistration().getRegistrationId());
    
    String provider = userRequest.getClientRegistration().getRegistrationId();
    SocialUserInfo socialUserInfo = null;
    if(provider.equals("google")) {
      socialUserInfo = new GoogleUserInfo(oauth2UserInfo) ;
    }
    Member returnMember = null;
    Optional<Member> findMember = memberRepository.findByUserId(socialUserInfo.getProviderId());
    if(findMember.isPresent()) {
      returnMember= findMember.get();
    } else {
      Member member = Member.builder()
                      .userId(socialUserInfo.getProviderId())
                      .userName(socialUserInfo.getName())
                      .userEmail(socialUserInfo.getEmail())
                      .role(Role.ROLE_USER)
                      .password(bCryptPasswordEncoder.encode(UUID.randomUUID().toString()))
                      .build();
        memberRepository.save(member);
    }
		return new CustomUserDetails(returnMember, oAuth2User.getAttributes());
	}
}
