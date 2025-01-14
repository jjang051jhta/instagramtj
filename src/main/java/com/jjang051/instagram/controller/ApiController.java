package com.jjang051.instagram.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jjang051.instagram.dto.CommentDto;
import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.entity.Comment;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.service.CommentService;
import com.jjang051.instagram.service.LikeService;
import com.jjang051.instagram.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
  private final MemberService memberService;
  private final CommentService commentService;
  private final LikeService likeService;
  


  @PutMapping("/member/{id}/profile")
  public Map<String,Object> profileChange(@PathVariable String id, MultipartFile profileImage) {
    log.info("profileImage==={}",profileImage);
    Member changedMember = memberService.changeProfile(id,profileImage);
    Map<String,Object> resultMap = new HashMap<>();
    if(changedMember!=null) {
      resultMap.put("isChangeProfile", true);
      resultMap.put("memberInfo",changedMember);
    }else {
      resultMap.put("isChangeProfile", false);
      resultMap.put("memberInfo",null);
    }
    return resultMap;
  }

  @PostMapping("/comment/write")
  public Map<String,Object> writeComment(@RequestBody CommentDto commentDto) {
      Comment comment = commentService.writeComment(commentDto);

      log.info("commentDto==={}",commentDto.toString());
      Map<String,Object> resultMap = new HashMap<>();
      if(comment!=null) {
        CommentDto responseCommentDto = new CommentDto(comment.getContent(), comment.getRegDate());
        resultMap.put("isInsert",true);
        resultMap.put("commentDto",responseCommentDto);
      } else {
        resultMap.put("isInsert",false);
        resultMap.put("commentDto",null);
      }
      //resultMap.put("commentList",commentList);
      return resultMap;
  }

  @DeleteMapping("/comment/delete/{id}")
  public Map<String,Object> deleteComment(@PathVariable("id") Integer id) {
    log.info("id==={}",id);
      boolean isDelete = commentService.deleteComment(id);
      Map<String,Object> resultMap = new HashMap<>();
      resultMap.put("isDelete",!isDelete);
      return resultMap;
  }

  @PostMapping("/image/{imageId}/like")
  public Map<String,Object> like(@PathVariable Integer imageId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
    int result = likeService.like(imageId,customUserDetails.getLoggedMember().getUserId());
    Map<String,Object> resultMap = new HashMap<>();
    if(result>0) {
      resultMap.put("isLikeInsert",true);
    } else {
      resultMap.put("isLikeInsert",false);
    }
    return resultMap;
  }
}
