package mock.project.backend.request;

import mock.project.backend.entities.Orders;
import mock.project.backend.entities.Products;

public class OrderDetailDTO {
	
	private Integer orderDetailId;
	private int quantity;
	private Products product;
	private Orders order;
	
	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	
}
