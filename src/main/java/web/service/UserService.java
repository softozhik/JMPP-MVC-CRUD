package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.model.UserRepository;

import java.util.List;

    @Service
    @Transactional
    public class UserService {


        @Autowired
        UserRepository repo;

        public void save(User user) {
            repo.save(user);
        }

        public List<User> listAll() {
            return (List<User>) repo.findAll();
        }

        public User get(Long id) {
            return repo.findById(id).get();
        }

        public void delete(Long id) {
            repo.deleteById(id);
        }

        public void update(Long id, User changeUser) {
            User updateUser = get(id);
            updateUser.setName(changeUser.getName());
            updateUser.setEmail(changeUser.getEmail());
            updateUser.setPassword(changeUser.getPassword());
        }
}
