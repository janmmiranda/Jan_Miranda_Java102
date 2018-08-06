package com.superstore.online;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

	public List<Product> readProductCSV(String fileName, String productName) throws IOException {
		
		List<Product> resultList = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		ProductCreator productCreator = new ProductCreator();
		
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			
			String line = br.readLine();
			line = br.readLine();
			
			while (line != null) {
				
				String[] attributes = line.split(",");
				
				if(attributes[1].equals(productName)) {
					
					Product tempProduct = productCreator.createProduct(attributes);
					resultList.add(tempProduct);
				} else if(productName.equals("All")) {
					
					Product tempProduct = productCreator.createProduct(attributes);
					resultList.add(tempProduct);
				}
				
				line = br.readLine();
			}
		}
		return resultList;		
	}
}
