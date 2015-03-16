package products;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "productData", eager = true)
@SessionScoped
public class ProductData implements Serializable {
   private static final long serialVersionUID = 1L;

   public List<Product> getProducts(){
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = getConnection();
      String stm = "Select * from products";
      
      List<Product> records = new ArrayList<Product>();
      try {   
         pst = con.prepareStatement(stm);
         pst.execute();
         rs = pst.getResultSet();
         
         while(rs.next()){
        	 Product product = new Product();
        	 product.setId(rs.getInt(1));
        	 product.setName(rs.getString(2));
        	 product.setDescription(rs.getString(3));
        	 product.setQuantity(rs.getInt(4));
            records.add(product);				
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return records;
   }
   
   public void addProduct(Product newProduct) {		
	   PreparedStatement ps = null;
	   Connection con = getConnection();
	   String sql = "INSERT INTO products(id, name, description, quantity) VALUES(?,?,?,?)";
	   try {
		   ps = con.prepareStatement(sql);
		   
		   int newProductId = 0;
		   List<Product> existingProducts = getProducts();
		   for(Product product : existingProducts) {
			   if(product.getId() > newProductId) {
				   newProductId = product.getId();
			   }
		   }
		   newProductId += 1;
		   
		   ps.setInt(1, newProductId);
		   ps.setString(2, newProduct.getName());
		   ps.setString(3, newProduct.getDescription());
		   ps.setInt(4, newProduct.getQuantity());
		   
		   int i = ps.executeUpdate();
		   
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
   }

   private Connection getConnection(){
      Connection con = null;

      String url = "jdbc:derby:/Users/Bonobo/MyDB";
      String user = "testUser";
      String password = "password";
      try {
    	  Class.forName("org.apache.derby.jdbc.EmbeddedDriver");  
         con = DriverManager.getConnection(url, user, password);
         System.out.println("Connection completed.");
      } catch (SQLException ex) {
    	  System.out.println("getConnection SQLException");
         System.out.println(ex.getMessage());
      } catch (ClassNotFoundException ex) {
    	  System.out.println("getConnection ClassNotFoundException");
          System.out.println(ex.getMessage());
      }
      finally{
      }
      return con;
   }
}
