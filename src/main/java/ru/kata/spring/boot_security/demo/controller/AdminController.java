package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/")
    public String getCredentials() {
        return "redirect:/login";
    }


    @GetMapping("/admin")
    public  String printUsersList(ModelMap model) {

        model.addAttribute("usersList", userService.getUsersList());
        model.addAttribute("title", userService.getAuthUser());
        model.addAttribute("roleList", roleService.getRolesList2());
        model.addAttribute("newUser", new User());
        return "players-action";
    }


    @PostMapping("/admin")
    public String createNewUser(@ModelAttribute("newUser")  User user, ModelMap model) {
        model.addAttribute("newUser");
        userService.addUser(user);

        return "redirect:/admin";
    }

    @PatchMapping("admin/{id}")
    public String editChosenUser(@ModelAttribute("user") User user) {
        userService.modifyUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
