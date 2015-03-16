package products;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.faces.bean.*;


@ManagedBean
public class Product
{	
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
		   PreparedStatement ps = null;
		   Connection con = ProductData.getConnection();
		   String sql = "INSERT INTO products(id, name, description, quantity) VALUES(?,?,?,?)";
		   try {
			   ps = con.prepareStatement(sql);
			   ps.setInt(1, this.id);
			   ps.setString(2, this.name);
			   ps.setString(3, this.description);
			   ps.setInt(4, this.quantity);
			   
			   int i = ps.executeUpdate();
			   
			   System.out.println("i: " + i);
			   
			   System.out.println("Data Added Successfully");
		   }
		   catch(Exception ex) {
			   System.out.println("addProduct Exception");
			   System.out.println(ex.getMessage());
		   }
		   finally {
			   try {
				   con.close();
				   ps.close();
			   }
			   catch (Exception ex) {
				   ex.printStackTrace();
			   }
		   }
		   
		   return "";
	}
}
