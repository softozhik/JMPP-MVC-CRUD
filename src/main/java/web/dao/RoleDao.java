package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.Role;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

//    List<Role> findAll();
//
//    Role getOne(Long id);

    Role findRoleByRole(String role);

//    Role findRoleByName(String roleName);
}
