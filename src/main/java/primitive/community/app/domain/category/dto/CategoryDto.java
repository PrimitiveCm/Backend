package primitive.community.app.domain.category.dto;

import java.util.Optional;
import lombok.Data;
import primitive.community.app.domain.category.entity.Category;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private int depth;
    private Long upperCategoryId;

    public static CategoryDto of(Category category) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getCategoryId());
        categoryDto.setName(category.getName());
        categoryDto.setDepth(category.getDepth());
        categoryDto.setUpperCategoryId(category.getUpperCategory() == null ? null : category.getUpperCategory().getCategoryId());

        return categoryDto;
    }
}