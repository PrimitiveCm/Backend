package primitive.community.app.domain.category.dto;

import lombok.Data;
import primitive.community.app.domain.category.entity.Category;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private int depth;
    private Long upperCategoryId;

    public static CategoryDto of(Category category) {
        this.id = category.getCategoryId();
        this.name = category.getName();
        this.depth = category.getDepth();
        this.upperCategoryId = category.getUpperCategory().getCategoryId();

        return this;
    }

}