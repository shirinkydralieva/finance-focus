package crdev.finance_focus.controller;

import crdev.finance_focus.dto.IncomeDto;
import crdev.finance_focus.dto.ResponseMessageAPI;
import crdev.finance_focus.enums.ResultCode;
import crdev.finance_focus.enums.ResultCodeAPI;
import crdev.finance_focus.service.IncomeService;
import crdev.finance_focus.service.impl.IncomeServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/incomes")
public class IncomeController {
    private final IncomeService service;
    @GetMapping
    public ResponseMessageAPI<List<IncomeDto>> getAll(@RequestParam Long accountId) {
        try { return new ResponseMessageAPI<>(
                service.getAll(accountId),
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
            System.out.printf("IncomeController: getAll() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении списка записей доходов пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseMessageAPI<IncomeDto> getById(@PathVariable Long id) {
        try { return new ResponseMessageAPI<>(
                service.getById(id),
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
            System.out.printf("IncomeController: getById() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении записи дохода пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @PostMapping
    public ResponseMessageAPI<IncomeDto> create (@RequestBody IncomeDto model) {
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
            System.out.printf("IncomeController: create() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при создании записи дохода пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @DeleteMapping("/delete")
    public ResponseMessageAPI<String> delete(@RequestParam Long id) {
        try { return new ResponseMessageAPI<>(
                service.deleteById(id),
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
            System.out.printf("IncomeController: delete() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при удалении записи дохода пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @PutMapping("/{id}/update")
    public ResponseMessageAPI<IncomeDto> updateCategory(@PathVariable Long id,
                               @RequestBody IncomeDto model) {
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
            System.out.printf("IncomeController: update() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при обновлении записи дохода пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
}


