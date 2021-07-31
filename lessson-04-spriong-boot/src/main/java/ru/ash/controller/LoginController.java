package ru.ash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ash.service.RoleService;
import ru.ash.service.UserService;

import javax.validation.Valid;

@Controller
public class LoginController {

    private final RoleService roleService;
    private final UserService userService;

    public LoginController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login-page";
    }

    @GetMapping("/access_denied")
    public String accessDenied(){
        return "access_denied";
    }

    @GetMapping("/registration")
    public String registrationNewUser(Model model){
        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", roleService.getDefaultRole());
        return "registration_form";
    }

    @PostMapping("/registration")
    public String createNewUser(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model){

        for (UserDto userDto : userService.findAll()) {
            if(user.getUsername().equals(userDto.getUsername())){
                model.addAttribute("roles", roleService.getDefaultRole());
                result.rejectValue("username", "", "Username already exists");
                return "user_form";
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("roles", roleService.getDefaultRole());
            return "registration_form";
        }

        if (!user.getPassword().equals(user.getMatchingPassword())) {
            model.addAttribute("roles", roleService.getDefaultRole());
            result.rejectValue("password", "", "Passwords are not correct");
            return "registration_form";
        }

        userService.save(user);
        model.addAttribute("user", user);
        return "registration_new_user";
    }
}
