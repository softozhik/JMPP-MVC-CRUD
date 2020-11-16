package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleDao roleDao;

//    public List<Role> findAll() {
//        return roleDao.findAll();
//    }

    public Role getOne(Long id) {
        return roleDao.getOne(id);
    }

    public void addRoles() {
        if (roleDao.existsById(1L) == false) {
            roleDao.save(new Role(1L, "ROLE_ADMIN"));
        }
        if (roleDao.existsById(2L) == false) {
            roleDao.save(new Role(2L, "ROLE_USER"));
        }
        if (roleDao.existsById(3L) == false) {
            roleDao.save(new Role(3L, "ROLE_OVER"));
        }
    }


}
