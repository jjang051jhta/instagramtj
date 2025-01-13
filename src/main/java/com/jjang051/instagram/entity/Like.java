package com.jjang051.instagram.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="table_like")
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;


  @ManyToOne
  @JoinColumn(name = "imageId")
  private Image image;


  @ManyToOne
  @JoinColumn(name="userId", referencedColumnName = "userId")
  private Member member;



  @CreatedDate
  private LocalDateTime regDate;

  @LastModifiedDate
  private LocalDateTime modifyDate;
}
