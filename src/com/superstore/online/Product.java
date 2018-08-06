package com.superstore.online;

public class Product {

	private int ProductCode;
	private String Name;
	private String Brand;
	private int Price;
	private int Stock;
	
	public Product(int productCode, String Name, String Brand, int price, int stock) {
		this.ProductCode = productCode;
		this.Name = Name;
		this.Brand = Brand;
		this.Price = price;
		this.Stock = stock;
	}

	public int getProductCode() {
		return ProductCode;
	}

	public String getName() {
		return Name;
	}

	public String getBrand() {
		return Brand;
	}

	public int getPrice() {
		return Price;
	}

	public int getStock() {
		return Stock;
	}
	
	public String reduceStock(int quantity) {
		
		String result;
		
		if(quantity <= this.Stock) {
			result = "Price for " + quantity + " " + this.getName() + " of " + this.getBrand() + " is " + quantity*this.Price;
		} else {
			result = "invalid quantity ordered, available stock: " + this.Stock;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Product details [ ProductCode: " + this.ProductCode + " | Name: " + this.Name
				+ " | Brand: " + this.Brand + " | Price: " + this.Price 
				+ " | Stock: " + this.Stock;
	}


}
