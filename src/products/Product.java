package products;

import javax.faces.bean.*;


@ManagedBean
public class Product
{	
	private String name;
	private String description;
	private String origin;
	private int quantity = 1;
	private int id;
	
	public Product() {
		ProductIO productIO = new ProductIO();
		this.id = productIO.getNewProductId();
	}
	
	public String getOrigin()
	{
		return(origin);
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getName()
	{
		return(name);
	}
	
	public void setName(String name)
	{
		this.name = name.trim();
	}
	
	public String getDescription()
	{
		return(description);
	}
	
	public void setDescription(String description)
	{
		this.description = description.trim();
	}
	
	public int getQuantity()
	{
		return(quantity);
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	public int getId()
	{
		return(id);
	}
	
	public String saveProduct() {
		ProductIO productIO = new ProductIO();
		productIO.saveProduct(this);
		return "";
	}
	
	public String deleteProduct() {
		System.out.println("Deleting " + name);
		ProductIO productIO = new ProductIO();
		productIO.deleteProduct(this);
		return "";
	}
}
