package org.example.blogsystem.Controller;

import jakarta.validation.Valid;
import org.example.blogsystem.ApiResponse.ApiResponse;
import org.example.blogsystem.Model.Comment;
import org.example.blogsystem.Service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blog-system/comment")

public class CommentController {

    private final CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping("/get")
    public ResponseEntity getComments(){
        return ResponseEntity.status(200).body(commentService.getComments());
    }
    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        commentService.addComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment added successfully!"));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment, Errors errors ){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        commentService.updateComment(id, comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment updated successfully!"));


    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id){

        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse("Comment deleted successfully!"));

    }
    ///////////////////////////

    //3: get all comment for one post by post_id
    @GetMapping("/get/post-comments/{post_id}")
    public ResponseEntity getPostComments(@PathVariable Integer post_id){

        List<Comment> comments = commentService.getPostComments(post_id);

        return ResponseEntity.status(200).body(comments);

    }

    //5: get comments for specific post in specific date range
    @GetMapping("/get/comments-by-date/{post_id}/{date1}/{date2}")
    public ResponseEntity getResentComments(@PathVariable Integer post_id, @PathVariable LocalDate date1,  @PathVariable LocalDate date2 ){

        List<Comment> comments = commentService.getCommentByDateRange(post_id, date1, date2);
        return ResponseEntity.status(200).body(comments);

    }









}
