package crdev.finance_focus.service.impl;

import crdev.finance_focus.dto.UserDto;
import crdev.finance_focus.entity.User;
import crdev.finance_focus.repo.UserRepo;
import crdev.finance_focus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    public void save(UserDto model) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(model.getPassword());
        userRepo.save(user);
    }

    public String register (UserDto model){
        save(model);
        return "Successful registration!";
    }

    public String logIn (UserDto model){
        try {
            User user = userRepo.findUserByUsernameAndPassword(model.getUsername(), model.getPassword());
            if (model.getUsername().equals(user.getUsername()) && model.getPassword().equals(user.getPassword())){
                return "Authorization successful!";
            } else {
                return "Invalid username or password!";
            }
        } catch (NullPointerException e){
            return "Invalid username or password!";
        }
    }

    public String userHome(UserDto model){
        try {
            User user = userRepo.findUserByUsernameAndPassword(model.getUsername(), model.getPassword());
            if (logIn(model).equals("Authorization successful!")){
                return "Welcome, " + user.getUsername() + "!" + "\nYour id: "+ user.getId() +"\nYour accounts: " + user.getAccounts();
            } else {
                return "Sorry, you are not authorized!";
            }
        } catch (NullPointerException e){
            return "Invalid username or password!";
        }
    }

    public String updateUserByUsername(String newUsername, UserDto model){
        try {
            User user = userRepo.findUserByUsernameAndPassword(model.getUsername(), model.getPassword());
            user.setUsername(newUsername);
            userRepo.save(user);
            return "Username updated!" + "\nYour new username is: " + user.getUsername();
        } catch (NullPointerException e) {
            return "Invalid username or password!";
        }
    }

    public String updateUserByPassword(String newPassword, UserDto model){
        try {
            User user = userRepo.findUserByUsernameAndPassword(model.getUsername(), model.getPassword());
            user.setPassword(newPassword);
            userRepo.save(user);
            return "Password updated!";
        } catch (NullPointerException e) {
            return "Invalid username or password!";
        }
    }

    public String deleteUser(UserDto model){
        try {
            User user = userRepo.findUserByUsernameAndPassword(model.getUsername(), model.getPassword());
            userRepo.delete(user);
            return "User deleted!";
        } catch (NullPointerException e) {
            return "Invalid username or password!";
        }
    }

    public User findUserById(Long id) {
        return userRepo.findUserById(id);
    }
}
