package com.jjang051.instagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjang051.instagram.dto.JoinDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.service.IMemberService;
import com.jjang051.instagram.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

  private String prefix="/member";

  private final IMemberService memberService;

  @GetMapping("/join")
  public String join(Model model) {
      model.addAttribute("joinDto", new JoinDto());
      return prefix+"/join";
  }
  @PostMapping("/join")
  public String join(@Valid @ModelAttribute JoinDto joinDto, BindingResult bindingResult) {
      log.info("joinDto==={}",joinDto);
      if(bindingResult.hasErrors()) {
        return prefix+"/join";
      }
      Member savedMember = memberService.join(joinDto);
      if(savedMember!=null) {
        return "redirect:/member/login";
      }
      return prefix+"/join";
  }

  @GetMapping("/login")
  public String login() {
      return prefix+"/login";
  }
}

