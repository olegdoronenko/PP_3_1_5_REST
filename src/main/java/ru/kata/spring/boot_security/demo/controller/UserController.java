package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public  String printUsersList(ModelMap model) {
        model.addAttribute("title", userService.getAuthUser());
        //model.addAttribute("usersList", userService.getUsersList());
        return "/player";
    }

//    @GetMapping("{id}")
//    public String printOneUser(@PathVariable("id") Long id, ModelMap model) {
//        model.addAttribute(userService.findUserById(id));
//        return "OLD-show-common";
//    }
}
