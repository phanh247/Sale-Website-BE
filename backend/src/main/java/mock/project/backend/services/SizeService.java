package mock.project.backend.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mock.project.backend.entities.Categories;
import mock.project.backend.entities.Sizes;
import mock.project.backend.repository.CategoryRepository;
import mock.project.backend.repository.SizeRepository;
import mock.project.backend.request.CategoryDTO;
import mock.project.backend.request.SizeDTO;
@Service
@Transactional
public class SizeService {
	
	@Autowired
	private SizeRepository sizeRepo;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ModelMapper modelMap;
	
	public List<Sizes> findAllSizes() {
		List<Sizes> sizes = sizeRepo.findAll();
		return sizes;
	}
	
	public List<Integer> findSizeByProductId(Integer productId){
		List<Integer> sizes = sizeRepo.findBySize(productId);
		return sizes ;
	}
	
	
}
