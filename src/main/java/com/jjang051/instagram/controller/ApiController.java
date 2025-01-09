package com.jjang051.instagram.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;


@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
  private final MemberService memberService;


  @PutMapping("/member/{id}/profile")
  public Map<String,Object> profileChange(@PathVariable String id, MultipartFile profileImage) {
    log.info("profileImage==={}",profileImage);
    Member changedMember = memberService.changeProfile(id,profileImage);
    Map<String,Object> resultMap = new HashMap<>();
    if(changedMember!=null) {
      resultMap.put("isChangeProfile", true);
      resultMap.put("memberInfo",changedMember);
    }else {
      resultMap.put("isChangeProfile", false);
      resultMap.put("memberInfo",null);
    }
    return resultMap;
  }
}
