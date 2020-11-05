package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
public class UsersController {

    @Autowired
    private User users;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public String allUsers (ModelMap model) {
        List<User> listUsers = userService.listAll();
//        System.out.println(listUsers);
        model.addAttribute("users", listUsers);
        return "users";
    }

    @RequestMapping("/new")
    public String newCustomerForm(Map<String, Object> model) {
        User user = new User();
        model.put("customer", user);
        return "new_customer";
    }

}
