package crdev.finance_focus.controller;

import crdev.finance_focus.dto.ResponseMessageAPI;
import crdev.finance_focus.dto.UserDto;
import crdev.finance_focus.enums.ResultCode;
import crdev.finance_focus.enums.ResultCodeAPI;
import crdev.finance_focus.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "API пользователя.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно зарегистрирован",
                    content = {
                            @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)
                            )
                    }
            )
        }
    )
    @Operation(
            summary = "Регистрация пользователя",
            description = "Регистрация пользователя. Возвращает зарегистрированного пользователя в формате json."
    )
    @PostMapping("/register")
    public ResponseMessageAPI<UserDto> register(@RequestBody UserDto model){
        try { return new ResponseMessageAPI<>(
                service.register(model),
                ResultCodeAPI.SUCCESS,
                null,
                "successful registration",
                ResultCode.OK.getHttpCode()
        );
        }catch (Exception e) {
            System.out.printf("UserController: register() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при регистрации пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно авторизован",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    }
            )
    }
    )
    @Operation(
            summary = "Вход пользователя",
            description = "Вход пользователя. Возвращает объект пользователя в json."
    )
    @GetMapping("/login")
    public ResponseMessageAPI<UserDto> logIn(@RequestBody UserDto model){
        try { return new ResponseMessageAPI<>(
                service.logIn(model),
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
            System.out.printf("UserController: logIn() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при входе в аккаунт пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные пользователя успешно обновлены",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Обновление данных пользователя",
            description = "Обновление данных пользователя. Возвращает обновленного пользователя в формате json."
    )
    @PutMapping("/{id}/update")
    public ResponseMessageAPI<UserDto> update(@PathVariable Long id, @RequestBody UserDto model){
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
            System.out.printf("UserController: update() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при обновлении данных пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно удален",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Удаление пользователя",
            description = "Удаление пользователя. Возвращает сообщение об успешном удалении пользователя."
    )
    @DeleteMapping("/delete")
    public ResponseMessageAPI<String> delete(@RequestParam Long id){
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
            System.out.printf("UserController: delete() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при удалении пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

}
