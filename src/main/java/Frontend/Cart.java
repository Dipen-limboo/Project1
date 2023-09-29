package Frontend;

public class Cart {
	private int cart_id;
	private int product_id;
	private int user_id;
	private int quantity;
	private double total_price;
	private double product_price;
	
	
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