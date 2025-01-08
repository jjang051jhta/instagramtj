package com.jjang051.instagram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests(
        (auth)->auth
          .requestMatchers("/","/member/join","/member/login","/css/**","/js/**","/images/**")
          .permitAll()
          .anyRequest()
          .authenticated()
        )
        .formLogin(
        (auth)->auth
          .loginPage("/member/login")
          .usernameParameter("userId")
          //.passwordParameter("password")
          .loginProcessingUrl("/member/login")
          .defaultSuccessUrl("/", true)
          .permitAll()
        )
      .csrf((csrf)->csrf.disable());
      return httpSecurity.build();
  }
}
