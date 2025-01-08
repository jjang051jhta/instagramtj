package com.jjang051.instagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {

  @GetMapping({"/","/index","/home"})
  public String getMethodName() {
      return "/index/index";
  }
}
