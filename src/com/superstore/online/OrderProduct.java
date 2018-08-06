package com.superstore.online;

import java.util.List;

public class OrderProduct {

	public String orderProduct(List<Product> productList, String brand, int quantity) {
		
		int checkStock;
		Product tempProduct = null;
		String message;
		
		for(Product tProduct : productList) {
			if(tProduct.getBrand().equals(brand)) {
				tempProduct = tProduct;
			}
		}
		if(!tempProduct.equals(null)) {
			message = tempProduct.reduceStock(quantity);
			
		} else {
			message = "Brand inputed is invalid, please select a valid brand for the product";
		}
		return message;
	}
}
