package com.jjang051.instagram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

  
  @Bean
  public AuthenticationFailureHandler failureHandler(){
      return new ForwardAuthenticationFailureHandler("/member/login");
  }
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
          .loginProcessingUrl("/member/login-process")
          .defaultSuccessUrl("/", true)
          .failureHandler(failureHandler())
          .permitAll()
        )
        .logout(
          (auth)->auth
          .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
          .logoutSuccessUrl("/")
          .invalidateHttpSession(true)
        )
        .csrf((csrf)->csrf.disable());
        return httpSecurity.build();
  }
}
