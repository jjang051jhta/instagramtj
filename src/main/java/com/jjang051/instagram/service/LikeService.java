package com.jjang051.instagram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjang051.instagram.repository.LikeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeService {

  private final LikeRepository likeRepository;

  @Transactional
  public int like(int imageId,String userId) {
    return likeRepository.like(imageId, userId);
  }

  @Transactional
  public int hate(int imageId,String userId) {
    return likeRepository.hate(imageId, userId);
  }
}
