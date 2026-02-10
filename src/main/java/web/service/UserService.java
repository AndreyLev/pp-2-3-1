package web.service;

import web.dto.AddUserRequestDto;
import web.dto.AddUserResponseDto;
import web.dto.UpdateUserRequestDto;
import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    AddUserResponseDto addUser(AddUserRequestDto requestDto);
    void removeUserById(Long id);
    void updateUser(UpdateUserRequestDto requestDto);
}
