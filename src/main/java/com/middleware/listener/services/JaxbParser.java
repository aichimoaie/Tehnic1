package com.middleware.listener.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



public class JaxbParser {

	private File file;

	public JaxbParser(File file) {
		this.file = file;
	}


	public void createNewVendorDocument(Product product,String fileOrderID) {
		
		String supplier=product.getSuppl();
		product.setSuppl(null);
		//product.setOrderID();
		File a = new File("/home/bogdan/Documents/Bucharest/webapi/src/main/resources/static/"+supplier+fileOrderID+".xml");
		try {
			if(!a.exists()) {
				a.createNewFile();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Products products = new Products();
		products.setProduct(new ArrayList<Product>());
		products.getProduct().add(product);

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(products,a);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public Orders getFullOrdersDocument() {
		//File a = new File("/home/bogdan/Documents/Bucharest/webapi/src/main/resources/static/"+fileName+".xml");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Orders orders = (Orders) jaxbUnmarshaller.unmarshal(getFile());
			return orders;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}



	private Products getFullVendorDocument(String fileName , String fileOrderID) {
		File a = new File("/home/bogdan/Documents/Bucharest/webapi/src/main/resources/static/"+fileName+ fileOrderID+ ".xml");
		//if(!a.exists()) 			

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Products products = (Products) jaxbUnmarshaller.unmarshal(a);
			return products;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void insertInVendorDocument(Product product, String fileOrderID) {

		String supplier = product.getSuppl();
		product.setSuppl(null);
		//product.setOrderID( );
		
		File a = new File("/home/bogdan/Documents/Bucharest/webapi/src/main/resources/static/"+supplier+ fileOrderID +".xml");
		Products products=getFullVendorDocument(supplier, fileOrderID);

		products.getProduct().add(product);

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(products, a);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}


	public void  splitOrderFile() {
		
		//researsch on regex and refactor to obtain the id part of inputFile name
		String[] part = this.getFile().getAbsolutePath().split("(?<=\\D)(?=\\d)");
		String[] subpart = part[1].split("\\.");
		String fileOrderID = subpart[0];

		List<Order> orders = getFullOrdersDocument().getOrder();
		
		
		//here is needing for researsch and refactor but requirements are met; the products ar listed desc by date and price
		//i may use ".thenComparing(Order::getProduct().getPrice()));" but don't work 
		for (Order subList : orders) {
			List<Product> products = subList.getProduct();
			Collections.sort(products, Comparator.comparing(Product::getPrice).reversed());
		}
		
		Collections.sort(orders, Comparator.comparing(Order::getCreatedOn).reversed());
				
		
		ArrayList<String> vendors = new ArrayList<String>();

		
		for (int i = 0; i < orders.size(); i++) {

			//System.out.println(orders.get(i).getCreated());
			//System.out.println(orders.get(i).getID());

			List<Product> product = orders.get(i).getProduct();
			for (int j = 0; j < product.size(); j++) {
				System.out.println(product.get(j));

				Product p = new Product();
				try {
					p = (Product) product.get(j).clone();
				
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				
				if(vendors.contains(product.get(j).getSuppl())) {
					//should add to existin
					insertInVendorDocument(p,fileOrderID);

				}
				else {
					//should create new
					createNewVendorDocument(p,fileOrderID);	
					vendors.add(product.get(j).getSuppl());
				}

			}
		}

	}



	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}