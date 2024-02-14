package crdev.finance_focus.service;

import crdev.finance_focus.dto.UserDto;
import crdev.finance_focus.entity.User;

import java.util.Optional;

public interface UserService {
    User save(UserDto model);

    UserDto register (UserDto model);
    UserDto logIn (UserDto model);
    UserDto userToDto(User user);

    UserDto update(Long id, UserDto model);

    String delete(Long id);
    Optional<User> findById(Long id);
}
