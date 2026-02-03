package web.service;

import web.dto.UserDto;
import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(long id);
    void saveUser(UserDto userDto);
    void removeUserById(long id);
    void updateUser(UserDto userDto);
}
