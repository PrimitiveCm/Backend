package primitive.community.app.domain.post.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import primitive.community.app.domain.category.dto.CategoryDto;
import primitive.community.app.domain.category.entity.Category;
import primitive.community.app.domain.category.repository.CategoryRepository;
import primitive.community.app.domain.post.dto.PostDto;
import primitive.community.app.domain.post.entity.Post;
import primitive.community.app.domain.post.repository.PostRepository;
import primitive.community.app.security.principal.MemberPrincipal;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public PostDto createPost(PostDto post, MemberPrincipal memberPrincipal) {
        Category category = categoryRepository.findById(post.getCategoryId()).orElseThrow();
        Post newPost = new Post(memberPrincipal.getMember(), category, post.getTitle(), post.getContent());

        postRepository.save(newPost);
        return post;
    }
}
