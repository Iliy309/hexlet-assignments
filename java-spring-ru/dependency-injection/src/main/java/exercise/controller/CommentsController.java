package exercise.controller;

import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping(path = "comments")
public class CommentsController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping
    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Comment getCommentsById(@PathVariable Long id){
        return commentRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment){
        commentRepository.save(comment);
        return  comment;
    }

    @PutMapping(path = "{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment body){
        var comment = commentRepository.findById(id)
                                       .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        comment.setPostId(body.getPostId());
        comment.setBody(body.getBody());
        return comment;
    }

    @DeleteMapping(path = "{id}")
    public String  deleteComment(@PathVariable Long id ){
        commentRepository.deleteById(id);
        return id + " comment was deleted";
    }
}
// END
