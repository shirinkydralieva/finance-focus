package crdev.finance_focus.controller;

import crdev.finance_focus.dto.AccountDto;
import crdev.finance_focus.dto.ExpenseDto;
import crdev.finance_focus.dto.ResponseMessageAPI;
import crdev.finance_focus.enums.ResultCode;
import crdev.finance_focus.enums.ResultCodeAPI;
import crdev.finance_focus.service.ExpenseService;
import crdev.finance_focus.service.impl.ExpenseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Tag(name = "Expense", description = "API транзакций расходов пользователя.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Получены все транзакции расходов пользователя по id счета/кошелька пользователя",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ExpenseDto.class)))
                    }
            )
    }
    )
    @Operation(
            summary = "Получение всех транзакций расходов пользователя по id счета/кошелька пользователя",
            description = "Получение всех транзакций расходов пользователя. Возвращает список транзакций расходов пользователя в формате json."
    )
    @GetMapping()
    public ResponseMessageAPI<List<ExpenseDto>> getAll(@RequestParam Long accountId) {
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
            System.out.printf("ExpenseController: getAll() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении списка записей расходов пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Транзакция расходов пользователя успешно получена",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExpenseDto.class)
                            )
                    }
            )
    }
    )
    @Operation(
            summary = "Получение транзакции расходов пользователя по id транзакции",
            description = "Получение транзакции расходов пользователя по id. Возвращает транзакцию расходов пользователя в формате json."
    )
    @GetMapping("/{id}")
    public ResponseMessageAPI<ExpenseDto> getById(@PathVariable Long id) {
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
            System.out.printf("ExpenseController: getById() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении записи расхода пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Транзакция расходов пользователя успешно создана",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExpenseDto.class)
                            )
                    }
            )
    }
    )
    @Operation(
            summary = "Создание транзакции расходов пользователя",
            description = "Создание транзакции расходов пользователя. Возвращает созданную транзакцию расходов пользователя в формате json."
    )
    @PostMapping()
    public ResponseMessageAPI<ExpenseDto> create (@RequestBody ExpenseDto model) {
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
            System.out.printf("ExpenseController: create() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при создании записи расхода пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Транзакция расходов пользователя успешно удалена",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExpenseDto.class)
                            )
                    }
            )
    }
    )
    @Operation(
            summary = "Удаление транзакции расходов пользователя по id транзакции",
            description = "Удаление транзакции расходов пользователя по id транзакции. Возвращает сообщение об удаленной транзакции расхода."
    )
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
            System.out.printf("ExpenseController: getById() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при удалении записи расхода пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Транзакция расходов пользователя успешно обновлена",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExpenseDto.class)
                            )
                    }
            )
    }
    )
    @Operation(
            summary = "Обновление транзакции расхода пользователя по id транзакции",
            description = "Обновление транзакции расхода пользователя по id. Возвращает обновленную транзакцию расхода пользователя в формате json."
    )
    @PutMapping("/{id}/update")
    public ResponseMessageAPI<ExpenseDto> update(@PathVariable Long id,
                                                 @RequestBody ExpenseDto model) {
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
            System.out.printf("ExpenseController: getById() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при обновлении записи расхода пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Получены все транзакции расходов пользователя по id счета/кошелька пользователя и по заданному периоду времени",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ExpenseDto.class)))
                    }
            )
    }
    )
    @Operation(
            summary = "Получение всех транзакций расходов пользователя по id счета/кошелька пользователя и заданному периоду времени",
            description = "Укажите начальную и конечную даты, также id счета/кошелька. Возвращает список транзакций расходов пользователя в формате json."
    )
    @GetMapping("/date-range")
    public ResponseMessageAPI<List<ExpenseDto>> findByAccountIdAndDateBetween(@RequestParam Long accountId, @RequestParam String startDate, @RequestParam String endDate){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date start = formatter.parse(startDate);
            Date end = formatter.parse(endDate);
            return new ResponseMessageAPI<>(
                service.findByAccountIdAndDateBetween(accountId, start, end),
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
            System.out.printf("ExpenseController: findByAccountIdAndDateBetween() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении списка расходов пользователя по датам",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Получены все транзакции расходов пользователя по id счета/кошелька пользователя и по заданному периоду времени",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ExpenseDto.class)))
                    }
            )
    }
    )
    @Operation(
            summary = "Получение всех транзакций расходов пользователя по id счета/кошелька пользователя и заданному периоду времени(конкретно неделя, месяц, год)",
            description = "Выберите период (week, month, year), также id счета/кошелька. Возвращает список транзакций расходов пользователя в формате json."
    )
    @GetMapping("/{period}")
    public ResponseMessageAPI<List<ExpenseDto>> findByAccountIdAndPeriod(@RequestParam Long accountId, @PathVariable String period){
        try {
            return new ResponseMessageAPI<>(
                    service.findByAccountIdAndPeriod(accountId, period),
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
            System.out.printf("ExpenseController: findByAccountIdAndPeriod() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении списка расходов пользователя по периоду(week, month, year)",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
}


