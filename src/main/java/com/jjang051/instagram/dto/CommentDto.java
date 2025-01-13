package com.jjang051.instagram.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
  private String content;

  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  private LocalDateTime regDate;
  private Integer imageId;

  public CommentDto(String content,LocalDateTime regDate) {
    this.content=content;
    this.regDate=regDate;
    //this.regDate = regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }
}
