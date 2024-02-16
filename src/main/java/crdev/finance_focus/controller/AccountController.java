package crdev.finance_focus.controller;

import crdev.finance_focus.dto.AccountDto;
import crdev.finance_focus.dto.ResponseMessageAPI;
import crdev.finance_focus.dto.UserDto;
import crdev.finance_focus.entity.Account;
import crdev.finance_focus.enums.ResultCode;
import crdev.finance_focus.enums.ResultCodeAPI;
import crdev.finance_focus.service.AccountService;
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

import java.util.List;

@Tag(name = "Account", description = "API счета(кошелька) пользователя.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Счет пользователя успешно создан",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Создание счета пользователя",
            description = "Создание счета/кошелька пользователя. Возвращает созданный счет пользователя в формате json."
    )
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
            System.out.printf("AccountController: create() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при создании счета(кошелька) пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех счетов пользователя успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDto.class))
                            )
                    }
            )
    }
    )
    @Operation(
            summary = "Получение всех счетов пользователя",
            description = "Получение всех счетов/кошельков пользователя. Возвращает список счетов пользователя в формате json."
    )
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные счета пользователя успешно получены",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Получение счета пользователя по id",
            description = "Получение счета/кошелька пользователя по id. Возвращает счет пользователя в формате json."
    )
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные счета успешно обновлены",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Обновление счета пользователя",
            description = "Обновление счета/кошелька пользователя. Возвращает обновленный счет пользователя в формате json."
    )
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Счет пользователя успешно удален",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Удаление счета пользователя",
            description = "Удаление счета/кошелька пользователя. Возвращает сообщение об успешном удалении счета пользователя."
    )
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
