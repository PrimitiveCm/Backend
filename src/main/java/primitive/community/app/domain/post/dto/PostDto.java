package primitive.community.app.domain.post.dto;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private Long memberId;
    private Long categoryId;
    private String title;
    private String content;
}
