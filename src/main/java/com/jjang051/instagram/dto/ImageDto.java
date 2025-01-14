package com.jjang051.instagram.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jjang051.instagram.entity.Comment;
import com.jjang051.instagram.entity.Member;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
 
  private Integer id;

  private String caption;

  private String imgUrl;

  
  @JsonIgnoreProperties({"imageList"})
  private Member member;


  
  @JsonIgnoreProperties({"image"})
  @OrderBy("regDate DESC, id ASC")
  private List<Comment> commentList;


  private boolean likeState;

  private Integer likeTotal;

  
  private LocalDateTime regDate;


  private LocalDateTime modifyDate;

  
  @Builder
  public ImageDto(Integer id,String caption, String imgUrl, Member member, boolean likeState,Integer likeTotal,LocalDateTime regDate,LocalDateTime modifyDate) {
    this.id=id;
    this.caption=caption;
    this.imgUrl = imgUrl;
    this.member = member;
    this.likeState = likeState;
    this.likeTotal = likeTotal;
    this.regDate= regDate;
    this.modifyDate = modifyDate;
  }
}
