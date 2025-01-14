package com.jjang051.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jjang051.instagram.entity.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like,Integer> {

  @Modifying
  @Query(value = "INSERT INTO TABLE_LIKE(id,imageId,userId,regDate) values "+
                 "(TABLE_LIKE_SEQ.nextval,:imageId,:userId,sysdate)",nativeQuery = true)
  int like(@Param("imageId") int imageId, @Param("userId") String userId);

  @Modifying
  @Query(value = "DELETE TABLE_LIKE WHERE imageId= :imageId and userId = :userId",nativeQuery = true)
  int hate(@Param("imageId") int imageId, @Param("userId") String userId);
}
