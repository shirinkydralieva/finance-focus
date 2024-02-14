package crdev.finance_focus.controller;

import crdev.finance_focus.dto.AccountDto;
import crdev.finance_focus.dto.ResponseMessageAPI;
import crdev.finance_focus.entity.Account;
import crdev.finance_focus.enums.ResultCode;
import crdev.finance_focus.enums.ResultCodeAPI;
import crdev.finance_focus.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;
    @PostMapping()
    public ResponseMessageAPI<AccountDto> create(@RequestBody AccountDto model){
        try { return new ResponseMessageAPI<>(
                service.create(model),
                ResultCodeAPI.SUCCESS,
                null,
                "success",
                ResultCode.OK.getHttpCode()
        );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    exception.getMessage(),
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("UserController: create() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при создании счета(кошелька) пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    @GetMapping()
    public ResponseMessageAPI<List<AccountDto>> getAll(@RequestParam Long userId){
        try { return new ResponseMessageAPI<>(
                service.getAll(userId),
                ResultCodeAPI.SUCCESS,
                null,
                "success",
                ResultCode.OK.getHttpCode()
        );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    exception.getMessage(),
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("AccountController: getAll() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении всех счетов(кошельков) пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    @GetMapping("/{id}")
    public ResponseMessageAPI<AccountDto> info(@PathVariable Long id){
        try { return new ResponseMessageAPI<>(
                service.info(id),
                ResultCodeAPI.SUCCESS,
                null,
                "success",
                ResultCode.OK.getHttpCode()
        );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    exception.getMessage(),
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("AccountController: info() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении данных счета(кошелька) пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @PutMapping("/{id}/update")
    public ResponseMessageAPI<AccountDto> update(@PathVariable Long id, @RequestBody AccountDto model){
        try { return new ResponseMessageAPI<>(
                service.update(id, model),
                ResultCodeAPI.SUCCESS,
                null,
                "success",
                ResultCode.OK.getHttpCode()
        );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    exception.getMessage(),
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("AccountController: update() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при обновлении данных счета(кошелька) пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    @DeleteMapping("/delete")
    public ResponseMessageAPI<String> delete(@RequestParam Long id) {
        try { return new ResponseMessageAPI<>(
                service.delete(id),
                ResultCodeAPI.SUCCESS,
                null,
                "success",
                ResultCode.OK.getHttpCode()
        );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    exception.getMessage(),
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("AccountController: delete() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при удалении счета(кошелька) пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
}
