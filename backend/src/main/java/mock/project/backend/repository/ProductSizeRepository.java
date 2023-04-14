package mock.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mock.project.backend.entities.ProductSize;
import mock.project.backend.entities.Products;

@Repository(value="productSizeRepo")
public interface ProductSizeRepository extends JpaRepository<ProductSize, Integer> {
	
	void deleteByProduct(Products product);
}
