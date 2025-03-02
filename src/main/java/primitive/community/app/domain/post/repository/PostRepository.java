package primitive.community.app.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import primitive.community.app.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
