package com.middleware.listener.services;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.middleware.listener.services.*;



public class Help {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Util u = new Util();
		//u.convertStringToTimestamp("2012-07-12T15:29:33.000");

		File a = new File("/home/bogdan/Documents/Bucharest/webapi/src/main/resources/static/orders23.xml");

		JaxbParser jax = new JaxbParser(a);

		//  jax.SimpleForloop();

		List<Order> tutorials = jax.getFullOrdersDocument("orders23").getOrder();

		ArrayList<String> vendors = new ArrayList<String>();
		// there also may add an arraylist for dates and sort and then reloop
		System.out.println("==============> 1. Simple For loop Example.");
		for (int i = 0; i < tutorials.size(); i++) {
			//System.out.println(tutorials.get(i));
			System.out.println(tutorials.get(i).getCreated());

			System.out.println(tutorials.get(i).getID());
			List<Product> product = tutorials.get(i).getProduct();
			for (int j = 0; j < product.size(); j++) {
				System.out.println("descr: " + product.get(j).getDescription());
				System.out.println("currency: " +product.get(j).getCur());
				System.out.println("gtin: " +product.get(j).getGtin());
				System.out.println("price : " +product.get(j).getPrice());
				System.out.println("suppl: " +product.get(j).getSuppl());
			}
		}
	}


}
