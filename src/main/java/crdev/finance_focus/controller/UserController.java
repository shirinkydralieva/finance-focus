package crdev.finance_focus.controller;

import crdev.finance_focus.dto.UserDto;
import crdev.finance_focus.service.UserService;
import crdev.finance_focus.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDto model){
       return userService.register(model);
    }

    @GetMapping("/login")
    public String logIn(@RequestBody UserDto model){
        return userService.logIn(model);
    }

    @GetMapping("/login/home")
    public String userHome(@RequestBody UserDto model){
        return userService.userHome(model);
    }

    @PutMapping("/newUsername")
    public String updateUserByUsername(@RequestParam String newUsername, @RequestBody UserDto model){
        return userService.updateUserByUsername(newUsername, model);
    }

    @PutMapping("/newPassword")
    public String updateUserByPassword(@RequestParam String newPassword, @RequestBody UserDto model){
        return userService.updateUserByPassword(newPassword, model);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody UserDto model){
        return userService.deleteUser(model);
    }

}
