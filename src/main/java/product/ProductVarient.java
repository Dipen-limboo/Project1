package product;

public class ProductVarient{
	private int variantId;
    private int productId;
    private String color;
    private String size;

    public ProductVarient(String color2, String size) {
		this.color = color2;
		this.size = size;
	}
	public int getVariantId() {
        return variantId;
    }
    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
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