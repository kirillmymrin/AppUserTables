package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import javax.validation.Valid;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public String getUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("user/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "userInfo";
    }

    @GetMapping("/new")
    public String getNewForm(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping
    public String save(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "newUser";
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String getEditForm(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "edit";
        userService.update(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/";
    }
}
