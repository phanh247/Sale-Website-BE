package mock.project.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mock.project.backend.entities.Categories;
import mock.project.backend.entities.Products;
import mock.project.backend.repository.CategoryRepository;
import mock.project.backend.request.CategoryDTO;
import mock.project.backend.request.ProductDTO;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository catRepo;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ModelMapper modelMap;

	public List<CategoryDTO> findAllCategory() {
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		List<Categories> categories = catRepo.findAll();
		for (Categories category : categories) {
			CategoryDTO categoryDTO = modelMap.map(category, CategoryDTO.class);
			categoryDTOs.add(categoryDTO);
		}
		return categoryDTOs;
	}
	
}
