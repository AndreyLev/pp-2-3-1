package web.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    
    private Logger logger;
    
    private UserService userService;
    
    public UserController(@Qualifier("userService") UserService userService, Logger logger) {
        this.userService = userService;
        this.logger = logger;
    }
    
    @GetMapping("/")
    public String index(Model model) {
        logger.info("Пришел запрос на получение всех пользователей");
        
        List<User> users = userService.getAllUsers();
        
        logger.info("Получено {} пользователей", users.size());
        
        model.addAttribute("users", users);
        return "users";
    }
    
    @PostMapping("/add")
    public String saveUser(@RequestParam @NonNull String name,
                           @RequestParam @NonNull String lastName,
                           @RequestParam @NonNull Byte age,
                           Model model)
    {
        logger.info("Пришел запрос на сохранение пользователя");
        logger.info("Параметры | name={}, last_name={}, age={}", name, lastName, age);
        
        long id = userService.addUser(name, lastName, age);
        
        logger.info("ID добавленного пользователя: {}", id);
        
        model.addAttribute("user", id);
        return "fragments/userRow :: userRow";
    }
    
    @PostMapping("/remove")
    public ResponseEntity removeUser(@RequestParam Long id) {
        logger.info("Пришел запрос на удаление пользователя с id = {}", id);
        
        userService.removeUserById(id);
        
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/update")
    public ResponseEntity updateUser(
        @RequestParam Long id,
        @RequestParam String name,
        @RequestParam String lastName,
        @RequestParam Byte age)
    {
        logger.info("Пришел запрос на обновление пользователя");
        logger.info("Параметры: id = {}, name = {}, lastName = {}, age = {}", id, name, lastName, age);
        
        userService.updateUser(id, name, lastName, age);
        
        return ResponseEntity.ok().build();
    }
}
