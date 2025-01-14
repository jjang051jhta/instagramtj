package com.jjang051.instagram.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.dto.ImageUploadDto;
import com.jjang051.instagram.entity.Image;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
  private final ImageRepository imageRepository;

  @Value("${file.path}")
  String uploadFolder;

  public Image upload(ImageUploadDto imageUploadDto, CustomUserDetails customUserDetails) {
    String originalFilename = imageUploadDto.getFile().getOriginalFilename();
    UUID uuid = UUID.randomUUID();
    String imageFileName = uuid+"_"+originalFilename;
    Path  imageFilePath =  Paths.get(uploadFolder+imageFileName);
    //File originalFile = new File(uploadFolder+imageFileName);
    try {
      Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
      Image saveImage = Image.builder()
                        .caption(imageUploadDto.getCaption())
                        .member(customUserDetails.getLoggedMember())
                        .imgUrl("/upload/"+imageFileName)
                        .build();
      return  imageRepository.save(saveImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
    
  }

  public Image getDetail(Integer id) {
    Optional<Image> optionalImage = imageRepository.findById(id);
    Image findedImage = null;
    if(optionalImage.isPresent()) {
      findedImage = optionalImage.get();
      return findedImage;
    }
    return null;
  }

  public List<Image> story() {
    return imageRepository.findAll();
  }
}
