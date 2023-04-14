package mock.project.backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Order;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.server.ServerHttpSecurity.ExceptionHandlingSpec;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.jsf.FacesContextUtils;

import mock.project.backend.entities.ProductSize;
import mock.project.backend.entities.Products;
import mock.project.backend.entities.Sizes;
import mock.project.backend.entities.Status;
import mock.project.backend.entities.Users;
import mock.project.backend.repository.CategoryRepository;
import mock.project.backend.repository.OrderRepository;
import mock.project.backend.repository.SizeRepository;
import mock.project.backend.request.OrderDTO;
import mock.project.backend.request.ProductDTO;
import mock.project.backend.request.UserDTO;
import mock.project.backend.request.UserDTOReponse;
import mock.project.backend.response.ResponseTransfer;
import mock.project.backend.services.OrderService;
import mock.project.backend.services.ProductService;
import mock.project.backend.services.ProductSizeService;
import mock.project.backend.services.StatusService;
import mock.project.backend.services.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	private Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductSizeService productSizeService;

	@Autowired
	private CategoryRepository cateRepo;

	@Autowired
	private SizeRepository sizeRepo;

	@Autowired
	private ModelMapper modelMap;

	// check
	@GetMapping("/check")
	public String checkUser() {
		return "Deny";
	}

	// list all user
	@GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTOReponse>> finAllUser(
			@RequestParam(name = "page", required = false) Integer pageIndex) {
		List<Users> users = userService.findAllUser();
		List<Users> admin = userService.findAllAdmin();
		for (Users ad : admin) {
			users.remove(ad);
		}
		List<UserDTOReponse> userDTOs = new ArrayList<>();
		for (Users user : users) {
			UserDTOReponse userDTOResponse = modelMap.map(user, UserDTOReponse.class);
			userDTOs.add(userDTOResponse);
		}
		return ResponseEntity.ok(userDTOs);
	}

	// list all order
	@GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDTO>> finAllOrder(
			@RequestParam(name = "page", required = false) Integer pageIndex) {
		if (pageIndex == null || pageIndex == 0) {
			Pageable pageable = PageRequest.of(0, 5);
			return ResponseEntity.ok(orderService.findAllOrder(pageable));
		}
		Pageable pageable = PageRequest.of(pageIndex, 5);
		return ResponseEntity.ok(orderService.findAllOrder(pageable));
	}

	// update order status
	@PutMapping(value = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseTransfer updateStatusOrder(@RequestParam(name = "orderId") Integer orderId,
			@RequestParam(name = "statusId") Integer statusId) {
		logger.info("Searching order by orderId...");
		OrderDTO orderDTO = orderService.findOrderById(orderId);
		Status status = statusService.findStatusById(statusId);
		orderDTO.setStatus(status);
		orderService.save(orderDTO);
		return new ResponseTransfer("Updated successful!");

	}

	// update product by id
	@PutMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Products> updateProduct(@RequestBody ProductDTO product) {
		logger.info("Updating product.....");
		return ResponseEntity.ok(productService.save(product));
	}

	// add new product
	@PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO saveProducts(@RequestBody ProductDTO product,
			@RequestParam(name = "sizes", required = false) String size) {
		logger.info("Adding new product.....");
		List<Sizes> sizes = new ArrayList<>();
		String[] array = size.split(",");
		for (String letter : array) {
			if (letter.length() == 4 || letter == "" || letter.contains("null")) {
				continue;
			}
			sizes.add(sizeRepo.findById(Integer.valueOf(letter)).get());
		}
		product.setSizes(sizes);
		ProductDTO productDTO = modelMap.map(productService.save2(product), ProductDTO.class);
		return productDTO;
	}

	// list all product
	@GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> finAllProduct(
			@RequestParam(name = "page", required = false) Integer pageIndex) {
		if (pageIndex == null || pageIndex == 0) {
			Pageable pageable = PageRequest.of(0, 5);
			return ResponseEntity.ok(productService.findAllProduct(pageable));
		}
		Pageable pageable = PageRequest.of(pageIndex, 5);
		return ResponseEntity.ok(productService.findAllProduct(pageable));
	}

	// list product no paging
	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> finAllProductNoPaging() {
		return ResponseEntity.ok(productService.findAllProductNoPaging());
	}

	// delete product
	@DeleteMapping(value = "product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> detleteProduct(@PathVariable("id") Integer productId) throws Exception {
		Products product = productService.findById(productId);

		if (product == null) {
			new Exception("Product not found: " + productId);
			return ResponseEntity.ok(new ResponseTransfer("Could not found"));
		}
		productSizeService.deleteByProduct(product);
		productService.delete(product.getProductId());
		return ResponseEntity.ok(new ResponseTransfer("Delete successful!"));
	}
	//delete order
	@DeleteMapping(value = "order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseTransfer detleteOrder(@PathVariable("id") Integer productId) {
		ProductDTO product = productService.findPoductById(productId);
		if (product == null) {
			return new ResponseTransfer("Not found");
		}
		productService.delete(product.getProductId());
		return new ResponseTransfer("Delete successful!");
	}
}
