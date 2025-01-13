package com.jjang051.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjang051.instagram.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
