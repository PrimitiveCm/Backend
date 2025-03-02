package primitive.community.app.domain.post.dto;

import lombok.Data;

@Data
public class PostDto {
    private Long postId;
    private Long categoryId;
    private String title;
    private String content;
}
