package primitive.community.app.domain.post.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import primitive.community.app.domain.post.dto.PostDto;
import primitive.community.app.domain.post.service.PostService;
import primitive.community.app.security.principal.MemberPrincipal;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    private ResponseEntity<PostDto> createPost (@RequestBody PostDto post, @AuthenticationPrincipal MemberPrincipal member) {
        return new ResponseEntity<>(postService.createPost(post, member), HttpStatus.OK);
    }
}
