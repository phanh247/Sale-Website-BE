package mock.project.backend.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mock.project.backend.entities.Products;
import mock.project.backend.entities.Sizes;
import mock.project.backend.request.CategoryDTO;
import mock.project.backend.request.ProductDTO;
import mock.project.backend.request.ProductRequest;
import mock.project.backend.request.SizeDTO;
import mock.project.backend.response.ResponseTransfer;
import mock.project.backend.services.CategoryService;
import mock.project.backend.services.ProductService;
import mock.project.backend.services.SizeService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	private Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private SizeService sizeService;

	@Autowired
	private CategoryService categoryService;
	

	@Autowired
	private ModelMapper modelMap;

	// list all products
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> finAllProduct(
			@RequestParam(name = "page", required = false) Integer pageIndex) {
		if (pageIndex == null || pageIndex == 0) {
			Pageable pageable = PageRequest.of(0, 5);
			return ResponseEntity.ok(productService.findAllProduct(pageable));
		}
		Pageable pageable = PageRequest.of(pageIndex, 5);
		return ResponseEntity.ok(productService.findAllProduct(pageable));
	}
	//list product no  paging
	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> finAllProductNoPaging() {
		return ResponseEntity.ok(productService.findAllProductNoPaging());
	}

	// list product by search
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findProductBySearch(
			@RequestParam(name = "searchField", required = false) String searchField) {
		return ResponseEntity.ok(productService.findPoductBySearch(searchField));
	}
	
	// list product by category
	@GetMapping(value = "/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findProductByCategory(
			@PathVariable(name = "id", required = false) Integer categoryId,
			@RequestParam(name = "type", required = false) String type) {
		if (type == null || type == "") {
			return ResponseEntity.ok(productService.findPoductByCategory(categoryId));
		}
		return ResponseEntity.ok(productService.findPoductByCategoryAndType(categoryId, type));
	}
	
	// list product by type
	@GetMapping(value = "/gender", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findProductByType(
			@RequestParam(name = "type", required = false) String type) {
		return ResponseEntity.ok(productService.findPoductByType(type));
	}


	// list categories
	@GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDTO>> finAllCategory() {
		return ResponseEntity.ok(categoryService.findAllCategory());
	}

	// list sizes
	@GetMapping(value = "/sizes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sizes>> findAllSize() {
		return ResponseEntity.ok(sizeService.findAllSizes());
	}
	
	// list sizes by productId
	@GetMapping(value = "/{id}/sizes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Integer>> findSizeByProductId(@PathVariable("id") Integer productId) {
		List<Integer> sizes =sizeService.findSizeByProductId(productId);
		return ResponseEntity.ok(sizes);
		}

	// search product by filter
	@PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findProductByFilter(@RequestBody ProductRequest productRequest) {
		logger.info("Sorting products by filter...");
		return ResponseEntity.ok(productService.searchProductByFilter(productRequest));
	}

	// find product by id
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findProductById(@PathVariable("id") Integer id) throws Exception {
		logger.info("Searching product by id...");
		Products product =productService.findById(id);
		ProductDTO producctDTO = modelMap.map(product, ProductDTO.class);
		if(product == null) {
			 new Exception("Product not found: " + id);
			return ResponseEntity.ok(new ResponseTransfer("Could not found"));
		}
		return ResponseEntity.ok(producctDTO);
	}

	// list products by price ASC/DESC
	@GetMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> finAllProductByPrice(
			@RequestParam(name = "sort", required = false) String sort, @RequestParam(value="page", required = false) Integer pageIndex) {
		if (sort.contains("desc")  && pageIndex == null) {
			Pageable sortedByPriceDesc = PageRequest.of(0, 10, Sort.by("price").descending());
			return ResponseEntity.ok(productService.findAllProduct(sortedByPriceDesc));
		}
		if (sort.contains("desc")  && pageIndex != 0) {
			Pageable sortedByPriceDesc = PageRequest.of(pageIndex, 10, Sort.by("price").descending());
			return ResponseEntity.ok(productService.findAllProduct(sortedByPriceDesc));
		}
		if (sort.contains("asc") && pageIndex == null ) {
			Pageable sortedByPriceDesc = PageRequest.of(0, 10, Sort.by("price").ascending());
			return ResponseEntity.ok(productService.findAllProduct(sortedByPriceDesc));
		}
		if (sort.contains("asc")  && pageIndex != 0) {
			Pageable sortedByPriceDesc = PageRequest.of(pageIndex, 10, Sort.by("price").ascending());
			return ResponseEntity.ok(productService.findAllProduct(sortedByPriceDesc));
		}
		Pageable pageable = PageRequest.of(0, 10);
		return ResponseEntity.ok(productService.findAllProduct(pageable));
	}

	// list all products sort by date DESC
	@GetMapping(value = "/desc", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> finAllProductByDateDESC(
			@RequestParam(name = "page", required = false) Integer pageIndex) {
		if (pageIndex == null || pageIndex == 0) {
			Pageable sortedByDateDesc = PageRequest.of(0, 20, Sort.by("date").descending());
			return ResponseEntity.ok(productService.findAllProduct(sortedByDateDesc));
		}
		Pageable sortedByDateDesc = PageRequest.of(pageIndex, 10);
		return ResponseEntity.ok(productService.findAllProduct(sortedByDateDesc));
	}

}
