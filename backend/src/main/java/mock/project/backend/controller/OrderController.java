package mock.project.backend.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mock.project.backend.entities.Products;
import mock.project.backend.entities.Users;
import mock.project.backend.request.OrderDTO;
import mock.project.backend.request.ProductDTO;
import mock.project.backend.response.ResponseTransfer;
import mock.project.backend.services.OrderService;
import mock.project.backend.services.ProductService;
import mock.project.backend.services.UserService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	private Logger logger = Logger.getLogger(OrderController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ModelMapper modelMap;
	
	@PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseTransfer saveNewOrder(@RequestBody OrderDTO orderDTO,
			@RequestParam(value="username" ,required = false)String username,
			@RequestParam(value="productId" ,required = false)Integer productId) {
		Users user = modelMap.map(userService.findByUserName(username),Users.class);
		Products product = modelMap.map(productService.findById(productId),Products.class);	
		if(user ==null ||product==null) {
			return new ResponseTransfer("Somthing went wrong");}
		orderDTO.setUser(user);
		orderService.save(orderDTO);
		return new ResponseTransfer("Save successful");
	}
	
	@DeleteMapping(value="order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseTransfer detleteOrder(@PathVariable("id")Integer orderId){
		OrderDTO orderDTO = orderService.findOrderById(orderId);
		if(orderDTO==null) {
			return new ResponseTransfer("Not found");
		}
		orderService.delete(orderDTO.getOrderId());
		return new ResponseTransfer("Delete successful!");
	}
	
}
