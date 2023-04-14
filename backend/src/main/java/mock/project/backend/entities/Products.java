package mock.project.backend.entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Products implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	
	@Column(name="product_name",columnDefinition = "nvarchar(50)")
	private String productName;
	
	@Column(name="price")
	private double price;
	
	@Column(name="description", length = 50)
	private String description;
	
	@Column(name="type",columnDefinition = "nvarchar(50)")
	private String type;
	
	@Column(name="color",columnDefinition = "nvarchar(50)")
	private String color;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="date")
	private LocalDate date;
	
	@Column(name="brand", length = 50)
	private String brand;
	
	@ManyToOne
	@JoinColumn(name="category_id",referencedColumnName="category_id")
	private Categories category;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="product")
	private Set<Items> items;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="product")
	private Set<Images> images;	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="product")
	private Set<ProductSize> productSizes;	


	public Products() {
	super();
	}
	
	public Products(String productName, double price, String description, String type, String color, int quantity,
			String brand, Categories category) {
		super();
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.type = type;
		this.color = color;
		this.quantity = quantity;
		this.brand = brand;
		this.category = category;
	}
	
	public Products(String productName, double price, String description, String type, String color, int quantity,
			LocalDate date, String brand, Categories category, Set<Images> images) {
		super();
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.type = type;
		this.color = color;
		this.quantity = quantity;
		this.date = date;
		this.brand = brand;
		this.category = category;
		this.images = images;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public Set<Images> getImages() {
		return images;
	}

	public void setImages(Set<Images> images) {
		this.images = images;
	}


	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<Items> getItems() {
		return items;
	}

	public void setItems(Set<Items> items) {
		this.items = items;
	}
	
	
}
