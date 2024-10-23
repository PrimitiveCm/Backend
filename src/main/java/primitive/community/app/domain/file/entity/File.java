package primitive.community.app.domain.file.entity;

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
import primitive.community.app.domain.member.entity.Member;
import primitive.community.app.domain.post.entity.Post;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @Column(nullable = false)
    @Comment("파일 경로")
    private String fileUrl;

    @Column(nullable = false)
    @Comment("파일명")
    private String fileName;

    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    @Comment("게시글")
    private Post post;


    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @Comment("회원증명서")
    private Member member;
}
