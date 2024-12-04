package org.example.blogsystem.Controller;

import jakarta.validation.Valid;
import org.example.blogsystem.ApiResponse.ApiResponse;
import org.example.blogsystem.Model.Post;
import org.example.blogsystem.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blog-system/post")

public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/get")
    public ResponseEntity getPosts(){
        return ResponseEntity.status(200).body(postService.getPosts());
    }
    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("Post added successfully!"));


    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id , @RequestBody @Valid Post post, Errors errors ){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        postService.updatePost(id, post);
        return ResponseEntity.status(200).body(new ApiResponse("Post updated successfully!"));

    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity deletePost(@PathVariable Integer id){

        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Post deleted successfully!"));

    }
    /////////////////

    //1: get post by title
    @GetMapping("/get/by-title/{title}")
    public ResponseEntity getByTitle(@PathVariable String title){

        Post post = postService.getByTitle(title);
        return ResponseEntity.status(200).body(post);
    }

    //2: get all post by user_id
    @GetMapping("/get/by-user/{user_id}")
    public ResponseEntity postsByUser(@PathVariable Integer user_id){

        List<Post> posts = postService.postsByUser(user_id);
        return ResponseEntity.status(200).body(posts);
    }

    //4: get all post before date by date
    @GetMapping("/get/before-date/{date}")

    public ResponseEntity findBeforeDate(@PathVariable LocalDate date){

        List<Post> posts = postService.findBeforeDate(date);
        return ResponseEntity.status(200).body(posts);

    }

    //6: get post with no comments
    @GetMapping("/get/posts-without-comment")
    public ResponseEntity postsWithoutComment(){
        List<Post> posts = postService.postsWithoutComment();
        return ResponseEntity.status(200).body(posts);

    }

    //8: get number of posts made by user
    @GetMapping("/get/posts-by-user/{user_id}")

    public ResponseEntity getNumOfPostsByUser(@PathVariable Integer user_id){

        Integer num = postService.getNumOfPostsByUser(user_id);
        return ResponseEntity.status(200).body(num);


    }


}
