package crdev.finance_focus.controller;

import crdev.finance_focus.dto.IncomeDto;
import crdev.finance_focus.service.impl.IncomeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/income")
public class IncomeController {
    private final IncomeServiceImpl incomeService;

    @GetMapping
    public List<IncomeDto> getAll() {
        return incomeService.getAll();
    }

    @GetMapping("/{id}")
    public IncomeDto getById(@PathVariable Long id) {
        return incomeService.getById(id);
    }

    @PostMapping
    public ResponseEntity<String> save (@RequestBody IncomeDto model) {
        incomeService.save(model);
        return ResponseEntity.ok("Transaction (income) successfully saved!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        incomeService.deleteById(id);
        return ResponseEntity.ok("Transaction (income) successfully deleted!");
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<String> updateCategory(@RequestParam Long id,
                               @RequestParam String newCategory) {
        incomeService.updateCategory(id, newCategory);
        return ResponseEntity.ok("Income's category successfully updated!");
    }

    @PutMapping("/updateAmount")
    public ResponseEntity<String> updateAmount(@RequestParam Long id,
                                                 @RequestParam Double newAmount) {
        incomeService.updateAmount(id, newAmount);
        return ResponseEntity.ok("Income's amount successfully updated!");
    }


}


