package com.jjang051.instagram.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjang051.instagram.dto.JoinDto;
import com.jjang051.instagram.dto.LoginDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.service.IMemberService;
import com.jjang051.instagram.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
  public String login(Model model) {
      model.addAttribute("loginDto", new LoginDto());
      return prefix+"/login";
  }

  @PostMapping("/login")
  public String postLogin(@Valid @ModelAttribute LoginDto loginDto, BindingResult bindingResult, HttpServletRequest request){
        
        // 1. 로그인 실패 핸들러가 넣어준 exception 객체 꺼내기 (null이면 인증 예외가 없음) 
        AuthenticationException exception = (AuthenticationException) request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        
        // 2. AuthenticationException 확인하고 있으면 reject
        if(exception instanceof BadCredentialsException){
            bindingResult.reject("BadCredentials");
        }else if(exception instanceof UsernameNotFoundException){
          log.info("UserNotFound");
            bindingResult.reject("UserNotFound");
        }else if(exception != null){
            bindingResult.reject("AuthenticationException");
        }

		// 3. form 검증 혹은 로그인 실패로 생긴 exception이 있는 경우 
        if(bindingResult.hasErrors()){
            log.warn("errors={}",bindingResult.getAllErrors());
            log.info("faillllll");
            return "/member/login";
        }

		// 4. 검증 문제가 없으면 스프링 시큐리티의 loginProcessUrl로 forward
        return "forward:/member/login-process";
    }

    @GetMapping("/info")
    public String info() {
        return prefix+"/info";
    }
    
}

