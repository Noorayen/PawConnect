package com.mario.main.repository;

import com.mario.main.entity.Post;
import com.mario.main.entity.PostLike;
import com.mario.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByPostAndUser(Post post, User user);

    long countByPost(Post post);
}