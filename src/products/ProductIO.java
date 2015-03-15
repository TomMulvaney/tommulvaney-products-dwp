package products;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.faces.bean.*;

import org.apache.commons.io.FileUtils;

import com.thoughtworks.xstream.XStream;


@ManagedBean
public class ProductIO 
{
	private String xmlFileName = "productlist";
	
	XStream createProductXStream() {
		XStream xstream = new XStream();
		xstream.alias("product", Product.class);
		xstream.alias("products", ProductList.class);
		xstream.addImplicitCollection(ProductList.class, "list");
		
		return xstream;
	}
	
	public void saveProduct(Product newProduct) {
		ArrayList<Product> products = getProductList();
		products.add(newProduct);
		saveProductList(products);
	}
	
	public void saveProductList(ArrayList<Product> products) {
		try {
			ProductList list = new ProductList();
			
			for(Product product : products){
				list.add(product);
			}
			
			XStream xstream = createProductXStream();
			String xml = xstream.toXML(list);
			
			File f = new File(xmlFileName);
			
			FileUtils.writeStringToFile(f, xml);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Product> getProductList() {
		ArrayList<Product> existingProducts = new ArrayList<Product>();
		
		try {
			File f = new File(xmlFileName);
			
			if(f.exists()) {
				XStream xstream = createProductXStream();
				
				String xml = FileUtils.readFileToString(f);
				ProductList pList = (ProductList)xstream.fromXML(xml);
				
				existingProducts = pList.getList();
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return existingProducts;
	}
	
	public String printProducts() {
		ArrayList<Product> products = getProductList();
		for(Product product : products) {
			System.out.println(product.getName());
			System.out.println(product.getDescription());
			System.out.println(product.getQuantity());
		}
		
		return "product-list";
	}
	
	public int getNewProductId() {
		int highestId = 1;
		
		ArrayList<Product> products = getProductList();
		
		for(Product product : products) {
			if(product.getId() > highestId) {
				highestId = product.getId();
			}
		}
		
		return highestId + 1;
	}
	
	public void deleteProduct(Product productToDelete) {
		ArrayList<Product> products = getProductList();
		products.removeIf(p -> p.getId() == productToDelete.getId());
		saveProductList(products);
	}
}
