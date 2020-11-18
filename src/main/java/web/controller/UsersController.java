package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;


    @GetMapping(value = "/hello")
    public String hello(ModelMap model) {
        String messages = "Hello!";
        model.addAttribute("messages", messages);
        return "hello";
    }


    @GetMapping(value = "/admin")
    public String allUsers(ModelMap model) {
        List<User> listUsers = userDao.findAll();
        model.addAttribute("users", listUsers);
        return "admin";
    }


    @GetMapping("/admin/edit/{id}")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("allRoles", roleDao.findAll());
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/admin/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id,
                         @RequestParam(value = "allRoles") String [] roles) {
//        System.out.println(roles);
//        Set<Role> roleSet = new HashSet<>();
//        for (String roleName : roles) {
//            roleSet.add(roleDao.findRoleByRole(roleName));
//        }
//        user.setRoles(roleSet);
        System.out.println("изменение ролей");
        user.setRoles(userService.newRoles(roles));
        userService.update(id, user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user, ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleDao.findAll());
        return "new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "allRoles") String[] roles) {
        user.setRoles(userService.newRoles(roles));
        userDao.save(user);
        return "redirect:/admin";
    }


    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userDao.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String currentUser(ModelMap model, Authentication autUser) {
        User user = userService.findUserByUsername(autUser.getName());
        System.out.println("LoggedIn: " + user);
        model.addAttribute("user", user);
        return "user";
    }

    @ModelAttribute("logoutLink")
    @GetMapping(value = "logout")
    public String logout() {
        return "logout";
    }

}
