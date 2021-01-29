package com.middleware.listener.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

		 Pattern pattern = Pattern.compile("\\/([a-z]*)(\\d*)\\.", Pattern.CASE_INSENSITIVE);
		 Matcher matcher = pattern.matcher(this.getFile().getAbsolutePath());
		 if(!matcher.find()) {
			 //[Refactor]
			 //the input file name is not correctly formatted
			 //should insert in some sort of errpr logfile or anounce the admin
			 
		 }
		 String fileOrderID = matcher.group(2);
		
		
		List<Order> orders = getFullOrdersDocument().getOrder();
		
		//[Refactor]
		//here is needing for researsch and refactor but requirements are met; the products ar listed desc by date and price
		//i may use ".thenComparing(Order::getProduct().getPrice()));" but don't work 
		for (Order subList : orders) {
			List<Product> products = subList.getProduct();
			Collections.sort(products, Comparator.comparing(Product::getPrice).reversed());
		}
		
		Collections.sort(orders, Comparator.comparing(Order::getCreatedOn).reversed());
				
		//Store vendor files that are already created for a particular orders input file
		ArrayList<String> vendors = new ArrayList<String>();

		
		for (int i = 0; i < orders.size(); i++) {

			List<Product> product = orders.get(i).getProduct();
			for (int j = 0; j < product.size(); j++) {
				//System.out.println(product.get(j));

				Product p = new Product();
				try {
					p = (Product) product.get(j).clone();
					p.setOrderID(orders.get(i).getID());
				
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				
				if(vendors.contains(product.get(j).getSuppl())) {
					//insert into existing vendor file
					insertInVendorDocument(p,fileOrderID);

				}
				else {
					//create new vendor file and insert into it
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