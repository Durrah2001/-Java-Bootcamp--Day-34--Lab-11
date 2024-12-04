package org.example.blogsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(columnDefinition = "int")
    private Integer userId;    //must add user first

    @Column(columnDefinition = "int")
    private Integer postId;     //must add post first

    @Size(min=3, max = 100, message = "Comment must be in this character range (3-100).")
    @Column(columnDefinition = "varchar(100)")
    private String content;

    @JsonFormat
    @Column(columnDefinition = "DATE")
    private LocalDate commentDate;

    ////////////////////////////////


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public @Size(min = 3, max = 100, message = "Comment must be in this character range (3-100).") String getContent() {
        return content;
    }

    public void setContent(@Size(min = 3, max = 100, message = "Comment must be in this character range (3-100).") String content) {
        this.content = content;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }
}
