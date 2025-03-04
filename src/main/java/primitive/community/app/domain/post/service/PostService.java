package primitive.community.app.domain.post.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import primitive.community.app.domain.category.entity.Category;
import primitive.community.app.domain.category.repository.CategoryRepository;
import primitive.community.app.domain.member.entity.Member;
import primitive.community.app.domain.member.repository.MemberRepository;
import primitive.community.app.domain.post.dto.PostDto;
import primitive.community.app.domain.post.dto.PostGetListDto;
import primitive.community.app.domain.post.entity.Post;
import primitive.community.app.domain.post.repository.PostRepository;
import primitive.community.app.security.principal.MemberPrincipal;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    public PostDto createPost(PostDto post, MemberPrincipal memberPrincipal) {
        Category category = categoryRepository.findById(post.getCategoryId()).orElseThrow();
        Post newPost = new Post(memberPrincipal.getMember(), category, post.getTitle(), post.getContent());

        postRepository.save(newPost);
        return post;
    }

    @Transactional(readOnly = true)
    public List<PostGetListDto> getPostList(Long categoryId) {
        List<PostGetListDto> foundPostList = postRepository.findByCategory_CategoryId(categoryId);
        return foundPostList;
    }

    @Transactional(readOnly = true)
    public PostDto getPost(Long postId) {
        Post targetPost = postRepository.findById(postId).orElseThrow();
        return PostDto.of(targetPost);
    }

    public PostDto updatePost(PostDto postDto) {
        Post targetPost = postRepository.findById(postDto.getPostId()).orElseThrow();
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow();

        targetPost.updatePost(postDto.getTitle(), postDto.getContent(),category);
        return postDto;
    }

    public Long deletePost(Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }
}
