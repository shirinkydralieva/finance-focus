package crdev.finance_focus.service.impl;

import crdev.finance_focus.dto.CategoryDto;
import crdev.finance_focus.entity.Category;
import crdev.finance_focus.enums.TransactionType;
import crdev.finance_focus.repo.CategoryRepo;
import crdev.finance_focus.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo repo;

    @Override
    public Optional<Category> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Category create(CategoryDto model) {
        Category category = new Category();
        category.setName(model.getName());
        return repo.save(category);
    }

    @Override
    public List<CategoryDto> getAllExpenseCategory() {
        List<Category> categories = findAllByType(TransactionType.EXPENSE);
        List<CategoryDto> response = new ArrayList<>();
        for (Category category: categories){
            var model = categoryToDto(category);
            response.add(model);
        }
        return response;
    }

    @Override
    public List<CategoryDto> getAllIncomeCategory() {
        List<Category> categories = findAllByType(TransactionType.INCOME);
        List<CategoryDto> response = new ArrayList<>();
        for (Category category: categories){
            var model = categoryToDto(category);
            response.add(model);
        }
        return response;
    }
    @Override
    public CategoryDto categoryToDto(Category category) {
        var response = new CategoryDto();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }

    @Override
    public List<Category> findAllByType(TransactionType type) {
        return repo.findAllByType(type);
    }
}
