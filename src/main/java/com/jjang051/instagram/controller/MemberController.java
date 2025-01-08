package com.jjang051.instagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

  private String prefix="/member";

  @GetMapping("/join")
  public String join() {
      return prefix+"/join";
  }
}
