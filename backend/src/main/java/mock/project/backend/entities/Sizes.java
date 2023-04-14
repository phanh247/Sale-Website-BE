package mock.project.backend.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "size")
public class Sizes {
	@Id
	@Column(name = "size_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sizeId;
	
	@Column(name = "size")
	private int size;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="size")
	private Set<ProductSize> productSizes;	

	public Sizes() {
		super();
	}

	public Sizes(int size) {
		super();
		this.size = size;
	}

	public Integer getSizeId() {
		return sizeId;
	}

	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Sizes [sizeId=" + sizeId + ", size=" + size + "]";
	}
	
}
