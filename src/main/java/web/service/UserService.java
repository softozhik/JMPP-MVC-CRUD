package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.dao.UserDao;

import java.util.List;

    @Service
    @Transactional
    public class UserService {


        @Autowired
        UserDao userDao;


        public List<User> listAll() {
            return (List<User>) userDao.findAll();
        }

        public User getUser(Long id) {
            System.out.println(userDao.getOne(id));
            return userDao.getOne(id);
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
