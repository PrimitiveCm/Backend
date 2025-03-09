package primitive.community.app.domain.category.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import primitive.community.app.domain.category.dto.CategoryDto;
import primitive.community.app.domain.category.service.CategoryService;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategoryList() {
        return new ResponseEntity<>(categoryService.findCategoryList(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryDto), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Long> deleteCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.OK);
    }
}
