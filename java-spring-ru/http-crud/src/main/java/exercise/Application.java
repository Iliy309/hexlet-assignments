package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> show(){
        return  posts;
    }

    @GetMapping("/posts/{id}")
    Optional<Post> get(@PathVariable String id){
       return posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();
    }

    @PostMapping("/posts")
    Post save(@RequestBody Post body){
        var optionalPost = posts.stream()
                            .filter(post -> post.getId().equals(body.getId()))
                            .findFirst();
        if(optionalPost.isPresent()){
            posts.add(body);
        }

        return  body;
    }

    @PutMapping("/posts/{id}")
    Post update(@RequestBody Post body, @PathVariable String id){
        var optPost = posts.stream()
                        .filter(post -> post.getId().equals(id))
                        .findFirst();

        if (optPost.isPresent()) {
            var post = optPost.get();
            post.setBody(body.getBody());
            post.setTitle(body.getTitle());
            post.setId(body.getId());
        }
        return body;
    }

    @DeleteMapping("/posts/{id}")
    List<Post> delete(@PathVariable String id){
        var result  = posts.stream()
                .filter(post -> !post.getId().equals(id))
                .toList();
        return result;
    }


    // END
}
