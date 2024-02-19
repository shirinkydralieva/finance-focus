package crdev.finance_focus.controller;

import crdev.finance_focus.dto.ExpenseDto;
import crdev.finance_focus.dto.IncomeDto;
import crdev.finance_focus.dto.ResponseMessageAPI;
import crdev.finance_focus.enums.ResultCode;
import crdev.finance_focus.enums.ResultCodeAPI;
import crdev.finance_focus.service.IncomeService;
import crdev.finance_focus.service.impl.IncomeServiceImpl;
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

@Tag(name = "Income", description = "API транзакций доходов пользователя.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/incomes")
public class IncomeController {
    private final IncomeService service;
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список транзакций доходов пользователя успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = IncomeDto.class))
                            )
                    }
            )
    }
    )
    @Operation(
            summary = "Получение всех транзакций доходов пользователя по id счета/кошелька пользователя",
            description = "Получение всех транзакций доходов пользователя. Возвращает список транзакций доходов пользователя в формате json."
    )
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Транзакция дохода пользователя успешно получена",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = IncomeDto.class)
                            )
                    }
            )
    }
    )
    @Operation(
            summary = "Получение транзакции дохода пользователя по id транзакции",
            description = "Получение транзакции дохода пользователя по id. Возвращает транзакцию дохода пользователя в формате json."
    )
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Транзакция дохода пользователя успешно создана",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = IncomeDto.class)
                            )
                    }
            )
    }
    )
    @Operation(
            summary = "Создание транзакции дохода пользователя",
            description = "Создание транзакции дохода пользователя. Возвращает созданную транзакцию дохода пользователя в формате json."
    )
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Транзакция дохода пользователя успешно удалена",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = IncomeDto.class)
                            )
                    }
            )
    }
    )
    @Operation(
            summary = "Удаление транзакции дохода пользователя по id транзакции",
            description = "Удаление транзакции дохода пользователя по id. Возвращает сообщение об успешном удалении транзакции дохода пользователя."
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
            System.out.printf("IncomeController: delete() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при удалении записи дохода пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Транзакция дохода пользователя успешно обновлена",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = IncomeDto.class)
                            )
                    }
            )
    }
    )
    @Operation(
            summary = "Обновление транзакции дохода пользователя по id транзакции",
            description = "Обновление транзакции дохода пользователя по id. Возвращает обновленную транзакцию дохода пользователя в формате json."
    )
    @PutMapping("/{id}/update")
    public ResponseMessageAPI<IncomeDto> update(@PathVariable Long id,
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

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Получены все транзакции доходов пользователя по id счета/кошелька пользователя и по заданному периоду времени",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = IncomeDto.class)))
                    }
            )
    }
    )
    @Operation(
            summary = "Получение всех транзакций доходов пользователя по id счета/кошелька пользователя и заданному периоду времени",
            description = "Укажите начальную и конечную даты, также id счета/кошелька. Возвращает список транзакций расходов пользователя в формате json."
    )
    @GetMapping("/date-range")
    public ResponseMessageAPI<List<IncomeDto>> findByAccountIdAndDateBetween(@RequestParam Long accountId, @RequestParam String startDate, @RequestParam String endDate){
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
            System.out.printf("IncomeController: findByAccountIdAndDateBetween() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении списка доходов пользователя по датам",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Получены все транзакции доходов пользователя по id счета/кошелька пользователя и по заданному периоду времени",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = IncomeDto.class)))
                    }
            )
    }
    )
    @Operation(
            summary = "Получение всех транзакций доходов пользователя по id счета/кошелька пользователя и заданному периоду времени(конкретно неделя, месяц, год)",
            description = "Выберите период (week, month, year), также id счета/кошелька. Возвращает список транзакций расходов пользователя в формате json."
    )
    @GetMapping("/{period}")
    public ResponseMessageAPI<List<IncomeDto>> findByAccountIdAndPeriod(@RequestParam Long accountId, @PathVariable String period){
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
            System.out.printf("IncomeController: findByAccountIdAndPeriod() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении списка доходов пользователя по периоду(week, month, year)",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
}


