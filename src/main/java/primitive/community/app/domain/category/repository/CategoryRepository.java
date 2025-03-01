package primitive.community.app.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import primitive.community.app.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
