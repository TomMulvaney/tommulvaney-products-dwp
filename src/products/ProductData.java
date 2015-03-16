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
	
   private static ProductData instance;
   
   public static ProductData getInstance() {
	   return instance;
   }
   
   public ProductData() {
	   System.out.println("Constructor");
	   instance = this;
   }

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

   public static Connection getConnection(){
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
