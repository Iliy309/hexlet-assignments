package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping(path = "/posts")
public class PostsController {

    @Autowired
    private  PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<PostDTO> getAll(){
        var posts  = postRepository.findAll();
        return posts.stream()
                .map(this::toPostDTO)
                .toList();
    }

    @GetMapping(path = "/{id}")
    public PostDTO getPost(@PathVariable Long id ){
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        return toPostDTO(post);

    }

    public PostDTO toPostDTO(Post post){
        var postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());

        var comments = commentRepository.findByPostId(post.getId());
        var commentsDTO = comments.stream()
                .map(comment -> {
                    var commentDTO = new CommentDTO();
                    commentDTO.setId(comment.getId());
                    commentDTO.setBody(comment.getBody());
                    return  commentDTO;
                }).toList();
        postDTO.setComments(commentsDTO);
        return postDTO;
    }


}
// END
