package com.jjang051.instagram.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.dto.ImageUploadDto;
import com.jjang051.instagram.service.ImageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping("/image")
@Controller
@Slf4j
@RequiredArgsConstructor
public class ImageController {

  private final ImageService imageService;

  private String prefix="/image";

  @GetMapping("/upload")
  public String upload() {
      return prefix+"/upload";
  }

  @PostMapping("/upload")
  //@ResponseBody
  public String upload(ImageUploadDto imageUploadDto,@AuthenticationPrincipal CustomUserDetails customUserDetails) {
    log.info("imageUploadDto===={}",imageUploadDto);  
    imageService.upload(imageUploadDto,customUserDetails);
    return "redirect:/member/info/"+customUserDetails.getLoggedMember().getId();
  }
}
