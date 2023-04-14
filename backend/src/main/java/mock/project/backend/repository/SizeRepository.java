package mock.project.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mock.project.backend.entities.Sizes;
@Repository(value="sizeRepo")
public interface SizeRepository  extends JpaRepository<Sizes, Integer>{
	@Query("SELECT s.size FROM Sizes s "
			+ "LEFT JOIN ProductSize ps ON s.sizeId = ps.size.sizeId "
			+ "WHERE ps.product.productId =?1")
	List<Integer> findBySize(Integer productId);
}
