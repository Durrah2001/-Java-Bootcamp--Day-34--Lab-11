package org.example.blogsystem.Service;

import org.example.blogsystem.ApiResponse.ApiException;
import org.example.blogsystem.Model.Comment;
import org.example.blogsystem.Model.Post;
import org.example.blogsystem.Model.User;
import org.example.blogsystem.Repository.CommentRepository;
import org.example.blogsystem.Repository.PostRepository;
import org.example.blogsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }


    public void addComment(Comment comment){

        User user = userRepository.findUserByUserId(comment.getUserId());
        Post post = postRepository.findPostByPostId(comment.getPostId());

        if(user == null){
            throw  new ApiException("Can not add a comment without user!");
        }

        if(post == null){
            throw  new ApiException("Can not add a comment without post!");
        }

        commentRepository.save(comment);
    }

    public void updateComment(Integer id, Comment comment){

        Comment c = commentRepository.findCommentByCommentId(id);

        if(c == null){
            throw new ApiException("Comment with this ID not found to update it!");
        }

        User user = userRepository.findUserByUserId(comment.getUserId());
        Post post = postRepository.findPostByPostId(comment.getPostId());

        if(user == null){
            throw new ApiException("Can not update a comment without user!");
        }

        if(post == null){
            throw new ApiException("Can not update a comment without post!");
        }

        c.setContent(comment.getContent());
        c.setCommentDate(LocalDate.now());

        commentRepository.save(c);


    }

    public void deleteComment(Integer id){

        Comment c = commentRepository.findCommentByCommentId(id);

        if(c == null)
            throw new ApiException("Comment with this ID not found to delete it!");

        commentRepository.delete(c);
    }

    ///////////////////////////

    //3: get all comment for one post by post_id

    public List<Comment> getPostComments(Integer post_id){

        List<Comment> comments = commentRepository.getPostComments(post_id);

        if(comments.isEmpty())
            throw new ApiException("This post not found or doesn't have any comment yet!");

        return comments;
    }

    //5: get comments for specific post in specific date range

    public List<Comment> getCommentByDateRange(Integer post_id, LocalDate date1, LocalDate date2 ){

        List<Comment> comments = commentRepository.getCommentByDateRange(post_id, date1, date2 );

        if(comments.isEmpty())
            throw new ApiException("This post not found or doesn't have any comment in this date range!");
        return comments;
    }





}
