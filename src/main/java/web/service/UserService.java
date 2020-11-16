package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;
import web.model.User;
import web.dao.UserDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
    @Transactional
    public class UserService {


        @Autowired
        UserDao userDao;
        @Autowired
    RoleDao roleDao;


        public void addAdmin() {
            if (userDao.existsById(1L) == false) {
                Set<Role> role = new HashSet<>();
                role.add(roleDao.getOne(1L));
                User user = new User("admin", "admin", "admin", "admin@mail.11", role);
                userDao.save(user);
            }
        }

        public List<User> listAll() {
            return (List<User>) userDao.findAll();
        }

        public User getUser(Long id) {
            System.out.println(userDao.getOne(id));
            return userDao.getOne(id);
        }

        public User findUserByUsername(String username) {
            System.out.println(userDao.findUserByUsername(username));
            return userDao.findUserByUsername(username);
        }



        @Transactional
        public void update(Long id, User changeUser) {
            User updateUser = getUser(id);
            updateUser.setUsername(changeUser.getUsername());
            updateUser.setName(changeUser.getName());
            updateUser.setEmail(changeUser.getEmail());
            updateUser.setPassword(changeUser.getPassword());
            updateUser.setRoles(changeUser.getRoles());
        }
}
