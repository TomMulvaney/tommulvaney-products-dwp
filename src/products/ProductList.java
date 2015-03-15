package products;

import java.util.List;
import java.util.ArrayList;

public class ProductList {
	private List<Product> list;
	
	public ProductList(){
		list = new ArrayList<Product>();
	}
	
	public void add(Product p){
		list.add(p);
	}
	
	public ArrayList<Product> getList() {
		ArrayList<Product> copy = new ArrayList<Product>();
		
		for(Product product : list) {
			copy.add(product);
		}
		
		return copy;
	}
}
