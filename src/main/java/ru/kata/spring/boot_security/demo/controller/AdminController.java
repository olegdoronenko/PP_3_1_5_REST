package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public  String printUsersList(ModelMap model) {
        model.addAttribute("usersList", userService.getUsersList());
        return "players-action";
    }

    @GetMapping("{id}")
    public String printOneUser(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.findUserById(id));
        return "/show";
    }

    @GetMapping("new")
    public String printAddForm(@ModelAttribute("user") User user) {
        return "/new";
    }

    @GetMapping("{id}/edit")
    public String printEditForm(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.findUserById(id));
        return "/edit";
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user, ModelMap model) {
        userService.addUser(user);
        model.addAttribute("user");
        printOneUser(user.getId(), model);
        return "/show";
    }

    @PatchMapping("{id}")
    public String editChosenUser(@ModelAttribute("user") User user) {
        userService.modifyUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}