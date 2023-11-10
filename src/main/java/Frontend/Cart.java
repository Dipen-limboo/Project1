package Frontend;

public class Cart {
	private int cart_id;
	private int product_id;
	private int user_id;
	private int quantity;
	private double total_price, shipping, netTotal;
	private double product_price;
	private String product_image;
	private String product_name, ProductKeyword;
	private String lastname, email;
	private String firstname;
	
	
	
	public double getShipping() {
		return shipping;
	}
	public void setShipping(double shipping) {
		this.shipping = shipping;
	}
	public double getNetTotal() {
		return netTotal;
	}
	public void setNetTotal(double netTotal) {
		this.netTotal = netTotal;
	}

	public String getProductKeyword() {
		return ProductKeyword;
	}
	public void setProductKeyword(String productKeyword) {
		ProductKeyword = productKeyword;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProductImage() {
		return product_image;
	}
	public void setProductImage(String product_image) {
		this.product_image = product_image;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public int getCartId() {
		return cart_id;
	}
	public void setCartId(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return total_price;
	}
	public void setTotalPrice(double total_price) {
		this.total_price = total_price;
	}
}