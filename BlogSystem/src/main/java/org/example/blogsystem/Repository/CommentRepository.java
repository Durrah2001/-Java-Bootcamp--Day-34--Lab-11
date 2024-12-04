package org.example.blogsystem.Repository;

import org.example.blogsystem.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findCommentByCommentId(Integer id);

    @Query("select c from Comment c where c.postId = ?1")
    List<Comment> getPostComments(Integer id); //post id

    @Query("select c from Comment c where c.postId =?1 and c.commentDate between ?2 and ?3")
    List<Comment> getCommentByDateRange(Integer id, LocalDate date1, LocalDate date2); //post id
}
