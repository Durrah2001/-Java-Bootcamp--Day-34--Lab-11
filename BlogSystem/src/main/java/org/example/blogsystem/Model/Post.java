package org.example.blogsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(columnDefinition = "int")
    private Integer categoryId;     //must add category first

    @NotEmpty(message = "Post title can not be empty!")
    @Size(max= 30, message = "Title can not be more than 30 characters!")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String title;

    @NotEmpty(message = "Content can not be empty!")
    @Size(min = 10, max = 6000, message = "Post content must be in this character's range(10-6000).")
    @Column(columnDefinition = "varchar(6000) not null")
    private String content;

    @Column(columnDefinition = "int")
    private Integer userId;    //must add user first

    @JsonFormat
    @Column(columnDefinition = "DATE")
    private LocalDate publishDate;





    ///////////////////////////////


    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public @NotEmpty(message = "Post title can not be empty!") @Size(max = 30, message = "Title can not be more than 30 characters!") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "Post title can not be empty!") @Size(max = 30, message = "Title can not be more than 30 characters!") String title) {
        this.title = title;
    }

    public @NotEmpty(message = "Content can not be empty!") @Size(min = 10, max = 6000, message = "Post content must be in this character's range(10-6000).") String getContent() {
        return content;
    }

    public void setContent(@NotEmpty(message = "Content can not be empty!") @Size(min = 10, max = 6000, message = "Post content must be in this character's range(10-6000).") String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
