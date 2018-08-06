package com.superstore.online;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

public class CSVTask implements Callable<List<Product>> {

	private File file;
	private String productName;
	private List<Product> products;
	
	CSVTask(File file, String productName) {
		this.file = file;
		this.productName = productName;
	}
	
	@Override
	public List<Product> call() throws Exception {
		CSVReader csvReader = new CSVReader();
		products = csvReader.readProductCSV(this.file.toString(), this.productName);
		return products;
	} 
	

}
