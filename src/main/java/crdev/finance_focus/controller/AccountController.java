package crdev.finance_focus.controller;

import crdev.finance_focus.dto.AccountDto;
import crdev.finance_focus.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService service;

    @PostMapping("/create")
    public String create(@RequestBody AccountDto model, @RequestParam Long userId){
        return service.create(model, userId);
    }

    @GetMapping("/info")
    public String info(@RequestBody AccountDto model, @RequestParam Long userId){
        return service.info(model, userId);
    }

    @PutMapping("/updateName")
    public String updateName(@RequestParam Long userId,
                             @RequestParam String newName,
                             @RequestBody AccountDto model)
    { return service.updateName(userId, newName, model); }

    @PutMapping("/updateType")
    public String updateType (@RequestParam Long userId,
                              @RequestParam String newType,
                              @RequestBody AccountDto model)
    { return service.updateType(userId, newType, model); }

    @PutMapping("/updateBalance")
    public String updateBalance(@RequestParam Long userId,
                                @RequestParam Double newBalance,
                                @RequestBody AccountDto model)
    { return service.updateBalance(userId, newBalance, model); }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long userId, @RequestBody AccountDto model) {
        return service.delete(userId, model);
    }
}
