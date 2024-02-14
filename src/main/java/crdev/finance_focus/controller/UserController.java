package crdev.finance_focus.controller;

import crdev.finance_focus.dto.ResponseMessageAPI;
import crdev.finance_focus.dto.UserDto;
import crdev.finance_focus.enums.ResultCode;
import crdev.finance_focus.enums.ResultCodeAPI;
import crdev.finance_focus.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService service;

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

    @DeleteMapping("/delete")
    public ResponseMessageAPI<String> deleteUser(@RequestParam Long id){
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
