package com.jjang051.instagram.entity;

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


}
