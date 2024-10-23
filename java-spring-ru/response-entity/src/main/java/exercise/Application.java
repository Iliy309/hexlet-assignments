package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

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
    public  ResponseEntity<List<Post>> show(@RequestParam(defaultValue = "10") Integer limit){
        var listPost = posts.subList(0, limit);

        return  ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(limit))
                .body(listPost);
    }

    @GetMapping("/posts/{id}")
    public  ResponseEntity<Post> show(@PathVariable String id){
        var optPost = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();
        return ResponseEntity.of(optPost);
    }

    @PostMapping("/posts")
    public ResponseEntity create(@RequestBody Post newPost){
        var isAvaible = !posts.stream()
                            .filter(post -> post.getId().equals(newPost.getId()))
                            .findFirst()
                            .isPresent();

        return  ResponseEntity.status(isAvaible == true ? HttpStatus.CREATED:HttpStatus.ALREADY_REPORTED)
                       .body(newPost);
    }

    @PutMapping("/posts/{id}")
    public  ResponseEntity update(@PathVariable String id, @RequestBody Post body){
        var optPost = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();

        if (optPost.isPresent()){
            var findPost = optPost.get();
            findPost.setId(body.getId());
            findPost.setTitle(body.getTitle());
            findPost.setBody(body.getBody());
        } else {
            return  ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(body);
        }

        return ResponseEntity.ok()
                .body(body);

    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
