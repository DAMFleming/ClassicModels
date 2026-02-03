package model;

public class Product {
	
	private String productCode;
	private String productName;
	private String productLine;
	private String productScale;
	private String productVendor;
	private String productDescription;
	private short quantityInStock;
	private float buyPrice;
	private float MSRP;
	
	public Product(String productCode, String productName, String productLine, String productScale,
			String productVendor, String productDescription, short quantityInStock, float buyPrice, float mSRP) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productLine = productLine;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescription = productDescription;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		MSRP = mSRP;
	}

	public String getProductCode() {
		return productCode;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductLine() {
		return productLine;
	}

	public String getProductScale() {
		return productScale;
	}

	public String getProductVendor() {
		return productVendor;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public short getQuantityInStock() {
		return quantityInStock;
	}

	public float getBuyPrice() {
		return buyPrice;
	}

	public float getMSRP() {
		return MSRP;
	}
	
}
