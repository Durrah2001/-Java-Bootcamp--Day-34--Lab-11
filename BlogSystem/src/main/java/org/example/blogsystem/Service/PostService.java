package org.example.blogsystem.Service;

import org.example.blogsystem.ApiResponse.ApiException;
import org.example.blogsystem.Model.Category;
import org.example.blogsystem.Model.Post;
import org.example.blogsystem.Model.User;
import org.example.blogsystem.Repository.CategoryRepository;
import org.example.blogsystem.Repository.PostRepository;
import org.example.blogsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public void addPost(Post post){
        User user = userRepository.findUserByUserId(post.getUserId());
        Category category = categoryRepository.findCategoryByCategoryId(post.getCategoryId());

        if(user == null){
            throw  new ApiException("Can not add a post without user!");
        }

        if(category == null){
            throw  new ApiException("Can not add a post without category!");
        }

        postRepository.save(post);

    }

    public void updatePost(Integer id, Post post){

        Post p = postRepository.findPostByPostId(id);

        if(p == null){
            throw  new ApiException("Post with this ID not found to update it!");
        }

        User user = userRepository.findUserByUserId(post.getUserId());
        Category category = categoryRepository.findCategoryByCategoryId(post.getCategoryId());

        if(user == null){
            throw  new ApiException("Can not update a post without user!");
        }

        if(category == null){
            throw  new ApiException("Can not update a post without category!");
        }

        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        p.setPublishDate(LocalDate.now());

        postRepository.save(p);



    }

    public void deletePost(Integer id){

        Post p = postRepository.findPostByPostId(id);

        if(p == null)
            throw  new ApiException("Post with this ID not found to deleted it!");

        postRepository.delete(p);
    }
    ///////////End CRUD

    //1: get post by title
    public Post getByTitle(String title){

        Post p = postRepository.findPostByTitle(title);

        if(p==null)
            throw new ApiException("Post with this title not found!");

        return p;
    }
    //2: get all post by user_id
    public List<Post> postsByUser(Integer user_id){

        List<Post> postsByUser = postRepository.postsByUser(user_id);

        if(postsByUser.isEmpty())
            throw new ApiException("This user not found or doesn't have any post yet!");

        return postsByUser;
    }
    //4: get all post before date by date
    public List<Post> findBeforeDate(LocalDate date){

        List<Post> posts = postRepository.findByPublishDateBefore(date);

        if(posts.isEmpty())
            throw new ApiException("There is no post before this entered date");

        return posts;
    }

    //6: get post with no comments

    public List<Post> postsWithoutComment(){
        List<Post> posts = postRepository.getPostWithoutComment();
        return posts;
    }
    //8: get number of posts made by user

    public Integer getNumOfPostsByUser(Integer user_id){

        Integer num = postRepository.getNumOfPostsByUser(user_id);

        if(num == 0)
            throw new ApiException("This user not found or doesn't have any post yet!");

        return num;
    }




}
