package mock.project.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mock.project.backend.entities.Users;

@Repository(value="userRepo")
public interface UserRepository  extends JpaRepository<Users, Integer>{
	
//	Users findByUserNameAndPassword(String userName, String password);
	Users findByUserName(String userName);
	
	@Query("SELECT u FROM Users u "
			+ "LEFT JOIN UserRole ur ON u.userId = ur.user.userId "
			+ "WHERE NOT  ur.role.roleName ='ROLE_USER'")
	List<Users> findAllAdmin();
}