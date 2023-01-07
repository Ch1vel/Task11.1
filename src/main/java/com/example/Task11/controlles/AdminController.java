package com.example.Task11.controlles;

import com.example.Task11.models.User;
import com.example.Task11.services.RoleService;
import com.example.Task11.services.UserService;
import com.example.Task11.util.UserValidation;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

        private final UserService userService;
        private final RoleService roleService;

        private final UserValidation userValidation;

    public AdminController(UserService userService, RoleService roleService, UserValidation userValidation) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidation = userValidation;
    }

    @GetMapping("")
    public String userList(Authentication authentication,Model model) {
        model.addAttribute("users",userService.show());
        model.addAttribute("user",(User) authentication.getPrincipal());
        return "admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.show(id));
        return "admin";
    }

    @DeleteMapping("/user-delete")
    public String deleteUser(int id) {
        userService.delete(id);
        return "redirect:/admin";
    }


    @GetMapping("/add")
    public String add(@ModelAttribute("user") User user,Model model) {
        model.addAttribute("roles",roleService.showRoles());
        return "admin";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidation.validate(user,bindingResult);
        if(bindingResult.hasErrors()) return "admin";
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.show(id));
        return "admin";
    }

    @PatchMapping("/user-update")
    public String updateUser(User user){
        userService.add(user);
        return "redirect:/admin";
    }
}
