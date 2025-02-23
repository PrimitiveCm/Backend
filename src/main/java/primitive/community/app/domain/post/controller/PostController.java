package primitive.community.app.domain.post.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import primitive.community.app.domain.category.dto.CategoryDto;
import primitive.community.app.domain.post.dto.PostDto;
import primitive.community.app.domain.post.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, Long memeberId) {
        return new ResponseEntity<>(postService.createPost(postDto, memeberId), HttpStatus.OK);
    }
}
