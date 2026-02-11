package web.controller;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dto.AddUserRequestDto;
import web.dto.AddUserResponseDto;
import web.dto.UpdateUserRequestDto;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    
    private final Logger logger;
    
    private final UserService userService;
    
    public UserController(UserService userService, Logger logger) {
        this.userService = userService;
        this.logger = logger;
    }
    
    @GetMapping("/")
    public String index(Model model)
    {
        logger.info("Пришел запрос на получение всех пользователей");
        
        List<User> users = userService.getAllUsers();
        
        logger.info("Получено {} пользователей", users.size());
        
        model.addAttribute("users", users);
        return "users";
    }
    
    @PostMapping("/add")
    public String addUser(@Valid @RequestBody AddUserRequestDto requestDto, Model model)
    {
        logger.info("Пришел запрос на сохранение пользователя");
        logger.info("Параметры | {}", requestDto);
        
        AddUserResponseDto responseDto = userService.addUser(requestDto);
        
        logger.info("ID добавленного пользователя: {}", responseDto.getId());
        
        model.addAttribute("user", responseDto);
        return "fragments/userRow :: userRow";
    }
    
    @PostMapping("/remove")
    public ResponseEntity removeUser(@RequestParam @NonNull Long id)
    {
        logger.info("Пришел запрос на удаление пользователя с id = {}", id);
        
        userService.removeUserById(id);
        
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/update")
    public ResponseEntity updateUser(@Valid @RequestBody UpdateUserRequestDto requestDto)
    {
        logger.info("Пришел запрос на обновление пользователя");
        logger.info("Параметры: {}", requestDto);
        
        userService.updateUser(requestDto);
        
        return ResponseEntity.ok().build();
    }
}
