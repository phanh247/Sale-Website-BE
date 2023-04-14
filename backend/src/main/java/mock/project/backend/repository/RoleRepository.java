package mock.project.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mock.project.backend.entities.Roles;

@Repository(value="roleRepo")
public interface RoleRepository extends JpaRepository<Roles, Integer> {
	@Query("SELECT r.roleName FROM Roles r "
			+ "LEFT JOIN UserRole ur ON r.roleId = ur.role.roleId "
			+ "WHERE ur.user.userId =?1")
	List<String> findByRoleName(Integer userId);
	
}
