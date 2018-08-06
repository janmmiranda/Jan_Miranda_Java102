package com.superstore.online;

public class ProductCreator {

	Product createProduct(String[] metadata) {
		
		int productCode = Integer.parseInt(metadata[0]);
		String productName = metadata[1];
		String brand = metadata[2];
		int price = Integer.parseInt(metadata[3]);
		int stock = Integer.parseInt(metadata[4]);
		
		Product tempProduct = new Product(productCode, productName, brand, price, stock);
		return tempProduct;
	}
}
