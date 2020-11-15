package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UsersController {

    @Autowired
    private User users;
    @Autowired
    private Role role;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;


    @GetMapping(value = "/hello")
    public String hello (ModelMap model) {
        String messages = "Hello!";
        model.addAttribute("messages", messages);
        return "hello";
    }

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/users")
    public String allUsers (ModelMap model) {
//        List<User> listUsers = userService.listAll();
        List<User> listUsers = userDao.findAll();
        model.addAttribute("users", listUsers);
        return "users";
    }


    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("allRoles", roleDao.findAll());
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id,
                         @RequestParam(value = "allRoles") String [] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : roles) {
            roleSet.add(roleDao.findRoleByRole(roleName));
        }
        user.setRoles(roleSet);
        userService.update(id, user);
        return "redirect:/users";
    }



    @GetMapping ("/new")
    public String newUser (@ModelAttribute("user") User user, ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleDao.findAll());
        return "new";
    }

    @PostMapping()
    public String create (@ModelAttribute("user") User user,
                          @RequestParam(value = "allRoles") String [] roles) {

        Set<Role> roleSet = new HashSet<>();
        for (String roleName : roles) {
            roleSet.add(roleDao.findRoleByRole(roleName));
        }
        user.setRoles(roleSet);
        userDao.save(user);
        //System.out.println("++++++++++++++" + user.getPassword());
        return "redirect:/users";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userDao.deleteById(id);
        return "redirect:/users";
    }

}
