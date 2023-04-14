package mock.project.backend.request;

public class ProductRequest {
	
	private	String categoryName;
	private String color;
	private  double size;
	private String type;
	private double startRangePrice;
	private double endRangePrice;
	
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getStartRangePrice() {
		return startRangePrice;
	}
	public void setStartRangePrice(double startRangePrice) {
		this.startRangePrice = startRangePrice;
	}
	public double getEndRangePrice() {
		return endRangePrice;
	}
	public void setEndRangePrice(double endRangePrice) {
		this.endRangePrice = endRangePrice;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
