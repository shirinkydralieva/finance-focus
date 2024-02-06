package crdev.finance_focus.service;

import crdev.finance_focus.dto.UserDto;
import crdev.finance_focus.entity.User;

public interface UserService {
    void save(UserDto model);

    String register (UserDto model);
    String logIn (UserDto model);
    String userHome(UserDto model);

    String updateUserByUsername(String newUsername, UserDto model);

    String updateUserByPassword(String newPassword, UserDto model);

    String deleteUser(UserDto model);
    User findUserById(Long id);
}
