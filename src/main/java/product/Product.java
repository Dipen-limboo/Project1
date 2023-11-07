package product;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Product {
    private int product_id;
    private String product_name, product_image, product_keyword, product_description;
    private double product_price;
    private int product_quantity;
    private String type;
    private Timestamp  dateOrder;
    private String firstname, lastname;
    private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private List<String> colors; 
    private List<String> size;
    
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Timestamp dateOrder) {
        this.dateOrder = dateOrder;
    }

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


    public int getProductID() {
        return product_id;
    }

    public void setProductID(int product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public String getProductImage() {
        return product_image;
    }

    public void setProductImage(String product_image) {
        this.product_image = product_image;
    }

    public double getProductPrice() {
        return product_price;
    }

    public void setProductPrice(double product_price) {
        this.product_price = product_price;
    }

    public String getProductKeyword() {
        return product_keyword;
    }

    public void setProductKeyword(String product_keyword) {
        this.product_keyword = product_keyword;
    }

    public String getProductDescription() {
        return product_description;
    }

    public void setProductDescription(String product_description) {
        this.product_description = product_description;
    }

    public int getProductQuantity() {
        return product_quantity;
    }

    public void setProductQuantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

	public List<String> getSize() {
		return size;
	}

	public void setSize(List<String> size) {
		this.size = size;
	}



   
}
