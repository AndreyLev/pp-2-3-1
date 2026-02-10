package web.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.dto.AddUserRequestDto;
import web.dto.AddUserResponseDto;
import web.dto.UpdateUserRequestDto;
import web.exception.UserNotFoundException;
import web.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    
    private Logger logger;
    private UserDao userDao;
    
    public UserServiceImpl(UserDao userDao, Logger logger) {
        this.userDao = userDao;
        this.logger = logger;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userDao.findById(id);
    }
    
    @Override
    @Transactional
    public AddUserResponseDto addUser(AddUserRequestDto requestDto) {
        User user = new User(requestDto.getName(), requestDto.getLastName(), requestDto.getAge());
        userDao.saveUser(user);
        
        logger.info("Пользователь {} сохранен в БД", user);
        
        AddUserResponseDto responseDto = new AddUserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setName(user.getName());
        responseDto.setLastName(user.getLastName());
        responseDto.setAge(user.getAge());
        return responseDto;
    }
    
    @Override
    @Transactional
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
        
        logger.info("Пользователь с id = {} удален из БД", id);
    }
    
    @Override
    @Transactional
    public void updateUser(UpdateUserRequestDto requestDto) {
        Long userId = requestDto.getId();
        Optional<User> optionalUser = getUserById(userId);
        User user = optionalUser.orElseThrow(
            () -> new UserNotFoundException(
                "Ошибка обновления: пользователь с id = %d не найден".formatted(userId))
        );
        user.setName(requestDto.getName());
        user.setLastName(requestDto.getLastName());
        user.setAge(requestDto.getAge());
        userDao.updateUser(user);
        
        logger.info("Пользователь с id = {} обновлен", userId);
    }
    
}
