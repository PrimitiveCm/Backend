package primitive.community.app.domain.post.dto;

import lombok.Data;

@Data
public class PostGetListDto {
    private Long postId;
    private String title;
    private String categoryName;
    private Long categoryId;
    private Long memberId;
    private String memberName;

    public PostGetListDto(Long postId, String title, String name, Long categoryId, Long memberId, String userName) {
        this.postId = postId;
        this.title = title;
        this.categoryName = name;
        this.categoryId = categoryId;
        this.memberId = memberId;
        this.memberName = userName;
    }
}
