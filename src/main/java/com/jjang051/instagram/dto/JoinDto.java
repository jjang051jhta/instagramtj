package com.jjang051.instagram.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JoinDto {

  @NotBlank(message = "필수입력사항입니다.")
  @Size(min=5,max=20,message = "5글자이상 20글자이하로 작성해주세요.")
  private String userId;

  @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\\\":{}|<>])[A-Za-z0-9!@#$%^&*(),.?\\\":{}|<>]{8,20}$",
  message = "비밀번호는 8자 이상, 20자 이하이며, 숫자와 특수문자를 포함해야 합니다.")
  private String password;

  @Email(message = "이메일 형식에 맞게 적어주세요")
  @NotBlank(message = "필수입력사항입니다.")
  private String userEmail;

  @NotBlank(message = "필수입력사항입니다.")
  private String userName;
}
