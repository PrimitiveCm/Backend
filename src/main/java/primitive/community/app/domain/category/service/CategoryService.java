package primitive.community.app.domain.category.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import primitive.community.app.domain.category.dto.CategoryDto;
import primitive.community.app.domain.category.entity.Category;
import primitive.community.app.domain.category.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> findCategoryList() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for (Category category : categoryList) {
            categoryDtoList.add(CategoryDto.of(category));
        }

        return categoryDtoList;
    }
}
