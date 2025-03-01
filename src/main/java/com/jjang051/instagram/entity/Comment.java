package com.jjang051.instagram.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jjang051.instagram.constant.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "table_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @Column(length = 300, nullable = false)
  private String content;

  @ManyToOne
  @JoinColumn(name = "userId", referencedColumnName = "userId") 
  @JsonIgnoreProperties({"imageList"})
  private Member member; // 한명의 멤버는 여러개의 댓글을 달 수 있다.

  @ManyToOne
  @JoinColumn(name = "imageId")
  @JsonIgnoreProperties({"commentList","member","imageUrl"})
  private Image image;  // 이미지 하나에 여러개의 코켄트가 달릴 수 있다.

  @CreatedDate
  private LocalDateTime regDate;

  @LastModifiedDate
  private LocalDateTime modifyDate;
  
  @Builder
  public Comment(String content, Image image, Member member) {
    this.content=content;
    this.image=image;
    this.member=member;
  }
}
