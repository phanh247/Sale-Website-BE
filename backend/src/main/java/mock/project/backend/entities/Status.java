package mock.project.backend.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="status")
public class Status implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "status_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer statusId;	
	
	@Column(name="status",columnDefinition = "nvarchar(50)")
	private String status;
	
	@OneToOne(mappedBy = "status")
	private Orders order;

	public Status() {
		super();
	}
	
	public Status(String status) {
		super();
		this.status = status;
	}

	public Integer getStatusID() {
		return statusId;
	}

	public void setStatusID(Integer statusID) {
		this.statusId = statusID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}
	
}
