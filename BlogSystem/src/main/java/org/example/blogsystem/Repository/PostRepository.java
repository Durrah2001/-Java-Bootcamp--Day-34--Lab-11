package org.example.blogsystem.Repository;

import org.example.blogsystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findPostByPostId (Integer id);

    Post findPostByTitle(String title);

    @Query("select p from Post p where p.userId= ?1")
    List<Post> postsByUser(Integer id); //user id

    List<Post> findByPublishDateBefore (LocalDate date);

    @Query("select p from Post p left JOIN Comment c on p.postId=c.postId where c.commentId is null ")
    List<Post> getPostWithoutComment();

    @Query("select count(p) from Post p where p.userId = ?1")
    Integer getNumOfPostsByUser(Integer id); //user id



}
