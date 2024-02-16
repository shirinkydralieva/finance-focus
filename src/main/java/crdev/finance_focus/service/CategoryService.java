package crdev.finance_focus.service;

import crdev.finance_focus.dto.CategoryDto;
import crdev.finance_focus.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Long id);
    Category create(CategoryDto model);
    List<CategoryDto> getAll();
    CategoryDto categoryToDto(Category category);
}
