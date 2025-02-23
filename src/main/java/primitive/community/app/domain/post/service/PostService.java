package primitive.community.app.domain.post.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import primitive.community.app.domain.category.entity.Category;
import primitive.community.app.domain.category.repository.CategoryRepository;
import primitive.community.app.domain.post.dto.PostDto;
import primitive.community.app.domain.post.entity.Post;
import primitive.community.app.domain.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public PostDto createPost(PostDto postDto, Long memberId) {
        /*Category category = categoryRepository.findById(postDto.getCategoryId())
            .orElseThrow(() -> new NotFoundException());
        Post post = new Post();
*/
        return postDto;
    }

}
