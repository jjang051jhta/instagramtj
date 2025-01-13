package com.jjang051.instagram.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjang051.instagram.dto.CommentDto;
import com.jjang051.instagram.entity.Comment;
import com.jjang051.instagram.entity.Image;
import com.jjang051.instagram.repository.CommentRepository;
import com.jjang051.instagram.repository.ImageRepository;
import com.jjang051.instagram.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;
  private final ImageRepository imageRepository;
  //private final MemberRepository MemberRepository;
  

  @Transactional
  public Comment writeComment(CommentDto commentDto) {
    Optional<Image> optionalImage = imageRepository.findById(commentDto.getImageId());
    if(optionalImage.isPresent()) {
      Image findedImage =  optionalImage.get();
      Comment comment = Comment.builder()
                      .content(commentDto.getContent())
                      .image(findedImage)
                      .member(findedImage.getMember())
                      .build();
      return commentRepository.save(comment);
    }
    return null;
  }

  @Transactional
  public boolean deleteComment(Integer id) {
    commentRepository.deleteById(id);
    return commentRepository.existsById(id);
  }
}
