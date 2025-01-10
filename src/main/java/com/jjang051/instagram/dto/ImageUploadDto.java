package com.jjang051.instagram.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageUploadDto {
  private MultipartFile file;
  private String caption;
}
