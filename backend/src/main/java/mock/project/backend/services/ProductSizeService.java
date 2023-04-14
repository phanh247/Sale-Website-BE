package mock.project.backend.services;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mock.project.backend.entities.ProductSize;
import mock.project.backend.entities.Products;
import mock.project.backend.repository.ProductSizeRepository;
@Service
@Transactional
public class ProductSizeService {
	

	@Autowired
	private ProductSizeRepository productSizeRepo;

	@Autowired
	private ModelMapper modelMap;

	public ProductSize save(ProductSize productSize) {
		return productSizeRepo.save(productSize);
	}
	
	public void deleteByProduct(Products productId) {
		 productSizeRepo.deleteByProduct(productId);
	}
}
