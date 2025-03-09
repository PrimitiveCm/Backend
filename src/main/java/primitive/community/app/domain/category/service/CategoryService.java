package primitive.community.app.domain.category.service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import primitive.community.app.domain.category.dto.CategoryDto;
import primitive.community.app.domain.category.entity.Category;
import primitive.community.app.domain.category.repository.CategoryRepository;
import primitive.community.app.security.principal.MemberPrincipal;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDto> findCategoryList() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for (Category category : categoryList) {
            categoryDtoList.add(CategoryDto.of(category));
        }

        return categoryDtoList;
    }

    public Long createCategory(CategoryDto categoryDto) {
        Category upperCategory = null;
        int depth = 1;

        if(categoryDto.getUpperCategoryId() != null) {
            upperCategory = categoryRepository.findById(categoryDto.getUpperCategoryId()).orElseThrow();
            depth = upperCategory.getDepth() + 1;
        }

        Category newCategory = new Category(categoryDto.getName(), depth, upperCategory);
        Category savedCategory = categoryRepository.save(newCategory);

        return savedCategory.getCategoryId();
    }

    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category targetCategory = categoryRepository.findById(categoryDto.getId()).orElseThrow();
        Category upperCategory = (categoryDto.getUpperCategoryId() != null) ?
            categoryRepository.findById(categoryDto.getUpperCategoryId()).orElse(null) : null;

        targetCategory.updateCategory(categoryDto.getName(), categoryDto.getDepth(), upperCategory);
        return categoryDto;
    }

    public Long deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
        return categoryId;
    }
}
