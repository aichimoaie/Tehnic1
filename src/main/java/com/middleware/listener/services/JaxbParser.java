package com.middleware.listener.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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


	public void createNewVendorDocument(Product product, String fileName) {

		File a = new File("/home/bogdan/Documents/Bucharest/webapi/src/main/resources/static/"+fileName+".xml");
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



	private Products getFullVendorDocument(String fileName) {
		File a = new File("/home/bogdan/Documents/Bucharest/webapi/src/main/resources/static/"+fileName+".xml");
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

	public void insertInVendorDocument(Product product, String fileName) {

		File a = new File("/home/bogdan/Documents/Bucharest/webapi/src/main/resources/static/"+fileName+".xml");

		Products products=getFullVendorDocument(fileName);

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

		File a = new File("/home/bogdan/Documents/Bucharest/webapi/src/main/resources/static/orders23.xml");

		JaxbParser jax = new JaxbParser(a);

		List<Order> orders = jax.getFullOrdersDocument().getOrder();
		//sort desc
		Collections.reverse(orders);
		//Collections.sort(orders);
		
		ArrayList<String> vendors = new ArrayList<String>();

		System.out.println("==============> Loop through orders.");
		
		
		for (int i = 0; i < orders.size(); i++) {

			//System.out.println(orders.get(i).getCreated());
			//System.out.println(orders.get(i).getID());

			List<Product> product = orders.get(i).getProduct();
			for (int j = 0; j < product.size(); j++) {
				//				System.out.println("descr: " + product.get(j).getDescription());
				//				System.out.println("currency: " +product.get(j).getCurrency());
				//				System.out.println("gtin: " +product.get(j).getGtin());
				//				System.out.println("price : " +product.get(j).getPrice());
				//				System.out.println("suppl: " +product.get(j).getSuppl());

				if(vendors.contains(product.get(j).getSuppl())) {
					//should add to existin
					Product p;
					try {
						p = (Product) product.get(j).clone();
						insertInVendorDocument(p, p.getSuppl());
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}

				}
				else {
					//should create new
					Product p;
					try {
						p = (Product) product.get(j).clone();
						createNewVendorDocument(p, p.getSuppl());
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
					
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