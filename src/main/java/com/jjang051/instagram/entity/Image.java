package com.jjang051.instagram.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jjang051.instagram.constant.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Entity
@EntityListeners(AuditingEntityListener.class)

public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  private String caption;

  private String imgUrl;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private Member member;


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
