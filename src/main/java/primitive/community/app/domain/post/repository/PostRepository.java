package primitive.community.app.domain.post.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import primitive.community.app.domain.post.dto.PostGetListDto;
import primitive.community.app.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT new primitive.community.app.domain.post.dto.PostGetListDto(p.postId, p.title, c.name, c.categoryId, m.memberId, m.userName)"
        + "FROM Post p\n"
        + "JOIN p.category c\n"
        + "JOIN p.member m\n"
        + "WHERE c.categoryId = :categoryId")
    List<PostGetListDto> findByCategory_CategoryId(@Param("categoryId") Long categoryId);
}
