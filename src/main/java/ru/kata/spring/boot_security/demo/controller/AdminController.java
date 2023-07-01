package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public  String printUsersList(ModelMap model) {
        //User user = userService.getAuthUser();

        model.addAttribute("usersList", userService.getUsersList());
        model.addAttribute("title", userService.getAuthUser());
        model.addAttribute("rolesList", roleService.getRolesList());
        model.addAttribute("newUser", new User());
        return "players-action";
    }

    @GetMapping("{id}")
    public String printOneUser(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.findUserById(id));
        return "OLD-show";
    }

    @GetMapping("new")
    public String printAddForm(@ModelAttribute("user") User user, ModelMap model) {
        model.addAttribute("rolesList", roleService.getRolesList());
        return "OLD-new";
    }

//    @GetMapping("{id}/edit")
//    public String printEditForm(@PathVariable("id") Long id, ModelMap model) {
//        model.addAttribute("user", userService.findUserById(id));
//        return "OLD-edit";
//    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user, ModelMap model) {
        userService.addUser(user);
        model.addAttribute("user");
        printOneUser(user.getId(), model);
        return "redirect:/admin";
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
