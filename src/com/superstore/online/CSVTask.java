package com.superstore.online;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

public class CSVTask implements Callable<List<Product>> {

	private File file;
	private String productName;
	private List<Product> products;
	
	@Override
	public List<Product> call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	} 
	

}
