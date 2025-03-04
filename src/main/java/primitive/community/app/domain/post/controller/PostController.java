package primitive.community.app.domain.post.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import primitive.community.app.domain.post.dto.PostDto;
import primitive.community.app.domain.post.dto.PostGetListDto;
import primitive.community.app.domain.post.service.PostService;
import primitive.community.app.security.principal.MemberPrincipal;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost (@RequestBody PostDto post, @AuthenticationPrincipal MemberPrincipal member) {
        return new ResponseEntity<>(postService.createPost(post, member), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostGetListDto>> getPostList (@RequestParam Long categoryId) {
        return new ResponseEntity<>(postService.getPostList(categoryId), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost (@PathVariable Long postId) {
        return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.updatePost(postDto), HttpStatus.OK);
    }
}
