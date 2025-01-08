package com.jjang051.instagram.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jjang051.instagram.constant.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "instagram_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  private String userId;

  private String password;

  private String userEmail;

  private String userName;

  private String description;

  private String tel;

  @Enumerated(EnumType.STRING)
  private Role role;

  private String profileImage;

  @CreatedDate
  private LocalDateTime regDate;

  @LastModifiedDate
  private LocalDateTime modifyDate;


  @Builder
  public Member(String userId, String password, String userEmail, String userName, Role role) {
    this.userId=userId;
    this.userName=userName;
    this.userEmail = userEmail;
    this.password=password;
    this.role=role;
  }
}
