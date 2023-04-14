package mock.project.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mock.project.backend.entities.Orders;
@Repository(value="orderRepo")
public interface OrderRepository extends JpaRepository<Orders, Integer>{
	
	@Query("SELECT o FROM Orders o WHERE NOT user.userName = ?1")
	List<Orders> findByUserName(String userName);
	
	Optional<Orders> findById(Integer id);
	
}
