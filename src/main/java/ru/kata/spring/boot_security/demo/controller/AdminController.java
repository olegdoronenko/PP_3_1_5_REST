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
        User user = userService.getAuthUser();

        model.addAttribute("usersList", userService.getUsersList());
        model.addAttribute("title", userService.getAuthUser());
        //model.addAttribute("roleList", roleService.getRolesList());
        model.addAttribute("roleList", roleService.getRolesList2());
        model.addAttribute("newUser", new User());
//        System.out.println(roleService.getRolesList().equals(roleService.getRolesList2()));
//        System.out.println(roleService.getRolesList());
//        System.out.println(roleService.getRolesList2());
        return "players-action";
    }

//    @GetMapping("{id}")
//    public String printOneUser(@PathVariable("id") Long id, ModelMap model) {
//        model.addAttribute("user", userService.findUserById(id));
//        return "OLD-show";
//    }

//    @GetMapping("new")
//    public String printAddForm(@ModelAttribute("user") User user, ModelMap model) {
//        model.addAttribute("rolesList", roleService.getRolesList());
//        System.out.println("from printAddForm" + user);
//        return "redirect:/admin";
//    }

//    @GetMapping("{id}/edit")
//    public String printEditForm(@PathVariable("id") Long id, ModelMap model) {
//        model.addAttribute("user", userService.findUserById(id));
//        return "OLD-edit";
//    }

    @PostMapping("/admin")
    public String createNewUser(@ModelAttribute("newUser")  User user, ModelMap model) {
        model.addAttribute("newUser");
        //user.setRoles(user.getRoles());
        System.out.println("from create " + user);

        userService.addUser(user);

//        printOneUser(user.getId(), model);
        return "redirect:/admin";
    }

    @PatchMapping("{id}")
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
