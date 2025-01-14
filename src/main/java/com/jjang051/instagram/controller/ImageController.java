package com.jjang051.instagram.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.dto.ImageDto;
import com.jjang051.instagram.dto.ImageUploadDto;
import com.jjang051.instagram.entity.Image;
import com.jjang051.instagram.service.ImageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/detail/{id}")
  public String detail(@PathVariable Integer id, Model model) {
      Image findedImage = imageService.getDetail(id);
      ImageDto imageDto = ImageDto.builder()
                          .id(findedImage.getId())
                          .caption(findedImage.getCaption())
                          .imgUrl(findedImage.getImgUrl())
                          .likeTotal(findedImage.getLikes().size())
                          .member(findedImage.getMember())
                          .build();
      model.addAttribute("imageInfo", imageDto);
      return prefix+"/detail";
  }

  @GetMapping("/story")
  public String story(Model model) {
    List<Image> storyList = null;//imageService.story();
    model.addAttribute("storyList", storyList);
    return prefix+"/story";
  }
}
