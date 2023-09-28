package product;

import java.util.ArrayList;
import java.util.List;

public class Product {
	int product_id;
	String product_name, product_image, product_keyword, product_description;
	double product_price;
    private String color;
    private String size;
    
	
	
	
	public int getProductID() {
		return product_id;
	}
	public void setProductID(int id) {
		this.product_id = id;
	}
	
	
	
	public String getProductName() {
		return product_name;
	}
	public void setProductName(String name) {
		this.product_name = name;
	}
	
	
	
	public String getProductImage() {
		return product_image;
	}
	public void setProductImage(String image) {
		this.product_image = image;
	}
	
	
	
	public double getProductPrice() {
		return product_price;
	}
	public void setProductPrice(double price) {
		this.product_price = price;
	}
	
	
	
	public String getProductKeyword() {
		return product_keyword;
	}
	public void setProductKeyword(String keyword) {
		this.product_keyword = keyword;
	}
	
	
	
	public String getProductDescription() {
		return product_description;
	}
	public void setProductDescription(String description) {
		this.product_description = description;
	}
	
	public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
    	this.size = size;
    }

	
}