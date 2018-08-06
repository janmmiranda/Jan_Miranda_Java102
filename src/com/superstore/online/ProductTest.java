package com.superstore.online;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ProductTest {
	
	static ExecutorService executor;
	
	public static List<Future<List<Product>>> findProducts(File file, String productName,List<Future<List<Product>>> productTasks, CompletionService<List<Product>> CS) {
		
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File tempFile : files) {
				findProducts(tempFile, productName, productTasks, CS);
			}
		} else if (file.isFile()) {
			
			Callable<List<Product>> task = new CSVTask(file, productName);
			Future<List<Product>> future = CS.submit(task);
			productTasks.add(future);
		}
		return productTasks;
	}
	
	public static List<Product> findProductList(File fileName, String productName) throws InterruptedException, ExecutionException {
		
		System.out.println("\n\n\nLooking for product: " + productName + "...");
		
		CompletionService<List<Product>> CS = new ExecutorCompletionService<List<Product>>(executor);
		
		List<Future<List<Product>>> tasks = new ArrayList<Future<List<Product>>>();
		List<Future<List<Product>>> products = new ArrayList<>();
		
		tasks = findProducts(fileName, productName, products, CS);
		List<Product> resultProducts = new ArrayList<>();
		
		for (@SuppressWarnings("unused") Future<List<Product>> futureList : tasks) {
			
			List<Product> tempList = CS.take().get();
			for(Product tProduct : tempList) {
				resultProducts.add(tProduct);
			}
		}
		if(resultProducts.isEmpty()) {
			System.out.println("No Products found with product name: " + productName);
			executor.shutdown();
		} else {
			System.out.println("\nHere are the results...");
		}
		
		return resultProducts;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		executor = Executors.newFixedThreadPool(5);
		
		List<Product> laptopList = new ArrayList<>();
		List<Product> headphoneList = new ArrayList<>();
		File file1 = new File("C:\\Users\\janmiran\\Documents\\Products");
		
		laptopList = findProductList(file1, "Laptop");
		laptopList.forEach(l -> System.out.println(l.toString()));
		
		headphoneList = findProductList(file1, "Headphones");
		headphoneList.forEach(h -> System.out.println(h.toString()));
		
		OrderProduct orderProduct = new OrderProduct();
		System.out.println("\n\n\n\n" + orderProduct.orderProduct(laptopList, "Dell 3541", 10));
		System.out.println("\n\n\n" + orderProduct.orderProduct(headphoneList, "Apple", 20));
		
		executor.shutdown();
		if(executor.awaitTermination(30, TimeUnit.SECONDS)) {
			System.out.println("\n\n\nFinished all threads, goodbye");
		} else {
			System.out.println("\n\n\ntime out or interrupt occured");
		}
	}

}
