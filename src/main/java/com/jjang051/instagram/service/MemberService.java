package com.jjang051.instagram.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jjang051.instagram.constant.Role;
import com.jjang051.instagram.dto.JoinDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements IMemberService {


  @Value("${file.path}")
  String uploadFolder;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final MemberRepository memberRepository;

  @Override
  public Member join(JoinDto joinDto) {
    Member saveMember = Member.builder()
      .userId(joinDto.getUserId())
      .userName(joinDto.getUserName())
      .userEmail(joinDto.getUserEmail())
      .role(Role.ROLE_USER)
      .password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
    .build();
    return memberRepository.save(saveMember);
  }

  @Transactional
  public Member changeProfile(String userId, MultipartFile profileImage) {
    String originalFilename = profileImage.getOriginalFilename();
    UUID uuid = UUID.randomUUID();
    String imageFileName = uuid+"_"+originalFilename;
    Path  imageFilePath =  Paths.get(uploadFolder+imageFileName);
    //File originalFile = new File(uploadFolder+imageFileName);
    try {
      Files.write(imageFilePath,profileImage.getBytes());
      Optional<Member> optionalMember = memberRepository.findByUserId(userId);
      if(optionalMember.isPresent()) {
        Member findedMember = optionalMember.get();
        findedMember.updateProfile("/upload/"+imageFileName);
        return findedMember;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
