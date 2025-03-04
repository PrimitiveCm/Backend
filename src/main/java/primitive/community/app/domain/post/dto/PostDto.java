package primitive.community.app.domain.post.dto;

import lombok.Data;
import primitive.community.app.domain.post.entity.Post;

@Data
public class PostDto {
    private Long postId;
    private Long categoryId;
    private String title;
    private String content;

    public PostDto(Long postId, Long categoryId, String title, String content) {
        this.postId = postId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
    }

    public static PostDto of(Post post) {
        return new PostDto(post.getPostId(), post.getCategory().getCategoryId(), post.getTitle(), post.getContent());
    }

}
