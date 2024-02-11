package crdev.finance_focus.controller;

import crdev.finance_focus.dto.ExpenseDto;
import crdev.finance_focus.service.impl.ExpenseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseServiceImpl expenseService;

    @GetMapping
    public List<ExpenseDto> getAll() {
        return expenseService.getAll();
    }

    @GetMapping("/{id}")
    public ExpenseDto getById(@PathVariable Long id) {
        return expenseService.getById(id);
    }

    @PostMapping
    public ResponseEntity<String> save (@RequestBody ExpenseDto model) {
        expenseService.save(model);
        return ResponseEntity.ok("Transaction (expense) successfully saved!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        expenseService.deleteById(id);
        return ResponseEntity.ok("Transaction (expense) successfully deleted!");
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<String> updateCategory(@RequestParam Long id,
                                                 @RequestParam String newCategory) {
        expenseService.updateCategory(id, newCategory);
        return ResponseEntity.ok("Expense's category successfully updated!");
    }

    @PutMapping("/updateAmount")
    public ResponseEntity<String> updateAmount(@RequestParam Long id,
                                               @RequestParam Double newAmount) {
        expenseService.updateAmount(id, newAmount);
        return ResponseEntity.ok("Expense's amount successfully updated!");
    }



}


