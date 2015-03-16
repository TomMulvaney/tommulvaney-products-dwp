package products;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.faces.bean.*;


@ManagedBean
public class Product
{	
	@ManagedProperty(value="#{productData}")
	private ProductData productData;
	
	public ProductData getProductData() {
		return(productData);
	}
	
	public void setProductData(ProductData productData) {
		this.productData = productData;
	}
	
	private int id;
	private String name;
	private String description;
	private int quantity = 1;
	
	public int getId() {
		return(id);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return(name);
	}
	
	public void setName(String name) {
		this.name = name.trim();
	}
	
	public String getDescription() {
		return(description);
	}
	
	public void setDescription(String description) {
		this.description = description.trim();
	}
	
	public int getQuantity() {
		return(quantity);
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String addProduct() {
		productData.addProduct(this);
		return "";
	}
}
