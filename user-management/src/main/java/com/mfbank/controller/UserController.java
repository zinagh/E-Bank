package com.mfbank.controller;
import com.mfbank.dto.Userdto;
import com.mfbank.model.User;
import com.mfbank.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    IUserService userService;

    @GetMapping("/retrieve-all-users")
    public List<Userdto> retrieveAllUsers() {
        List<Userdto> listUserdtos = userService.retrieveAllUsers();
        return listUserdtos;
    }

    @GetMapping("/retrieve-user/{userName}")
    public Userdto retrieveUser(@PathVariable("userName") String userName) {
        Userdto userdto = userService.retrieveUser(userName);
        return userdto;
    }

    @PostMapping("/add-user")
    public void addUser(@RequestBody Userdto u) {
       userService.addUser(u);

    }

    @DeleteMapping("/remove-user/{userName}")
    public void removeUser(@PathVariable("userName") String userName) {
        userService.removeUser(userName);
    }

    @PutMapping("/modify-user")
    public User modifyUser(@RequestBody Userdto u) {
        User user = userService.modifyUser(u);
        return user;
    }

}
