package mock.project.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_size")
public class ProductSize {
	
	@Id
	@Column(name = "product_size_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productSizeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id",referencedColumnName = "product_id")
	private Products product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "size_id", referencedColumnName = "size_id")
	private Sizes size;
	
	public ProductSize() {
		super();
	}
	
	public ProductSize(Sizes size) {
		super();
		this.size = size;
	}

	public ProductSize( Products product, Sizes size) {
		super();
		this.product = product;
		this.size = size;
	}

	public Integer getProductSizeId() {
		return productSizeId;
	}

	public void setProductSizeId(Integer productSizeId) {
		this.productSizeId = productSizeId;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public Sizes getSize() {
		return size;
	}

	public void setSize(Sizes size) {
		this.size = size;
	}
	
}
