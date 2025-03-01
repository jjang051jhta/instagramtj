package com.jjang051.instagram.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jjang051.instagram.constant.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  private String caption;

  private String imgUrl;

  @ManyToOne
  @JoinColumn(name = "userId", referencedColumnName = "userId")  //보통의 경우 많은 쪽이 주인이 된다.
  @JsonIgnoreProperties({"imageList"})
  private Member member;


  @OneToMany(mappedBy = "image")
  @JsonIgnoreProperties({"image"})
  @OrderBy("regDate DESC, id ASC")
  private List<Comment> commentList;


  @OneToMany(mappedBy = "image")
  @JsonIgnoreProperties({"image"})
  private List<Like> likes;


  @Transient
  private boolean likeState;

  @Transient
  private Integer likeTotal;

  
  @CreatedDate
  private LocalDateTime regDate;

  @LastModifiedDate
  private LocalDateTime modifyDate;

  
  @Builder
  public Image(String caption, String imgUrl, Member member) {
    this.caption=caption;
    this.imgUrl = imgUrl;
    this.member = member;
  }
}
