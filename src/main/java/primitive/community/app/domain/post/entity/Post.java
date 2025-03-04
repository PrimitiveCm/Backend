package primitive.community.app.domain.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import primitive.community.app.domain.category.entity.Category;
import primitive.community.app.domain.member.entity.Member;
import primitive.community.app.domain.post.dto.PostDto;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    @Comment("멤버")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Comment("카테고리")
    private Category category;

    @Column(nullable = false)
    @Comment("제목")
    private String title;

    @Comment("내용")
    private String content;

    public Post(Member member, Category category, String title, String content) {
        this.member = member;
        this.category = category;
        this.title = title;
        this.content = content;
    }
}
