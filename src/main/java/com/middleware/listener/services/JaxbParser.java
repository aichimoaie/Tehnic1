package com.middleware.listener.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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


	private Tutorials getFullDocument() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Tutorials.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Tutorials tutorials = (Tutorials) jaxbUnmarshaller.unmarshal(this.getFile());
			return tutorials;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}



	public void createNewDocument(Tutorial tutorial) {
		Tutorials tutorials = new Tutorials();
		tutorials.setTutorial(new ArrayList<Tutorial>());
		tutorials.getTutorial().add(tutorial);

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Tutorials.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(tutorials, file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public void insertInDocument(Tutorial tutorial) {
		Tutorials tutorials =getFullDocument();

		tutorials.getTutorial().add(tutorial);

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Tutorials.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(tutorials, file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

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
	
	public Orders getFullOrdersDocument(String fileName) {
		File a = new File("/home/bogdan/Documents/Bucharest/webapi/src/main/resources/static/"+fileName+".xml");
			//if(!a.exists()) 			

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Orders orders = (Orders) jaxbUnmarshaller.unmarshal(a);
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
		
		Products products =getFullVendorDocument(fileName);

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






	public void  SimpleForloop() {
		List<Tutorial> tutorials =getFullDocument().getTutorial();

		ArrayList<String> vendors = new ArrayList<String>();
		// there also may add an arraylist for dates and sort and then reloop
		System.out.println("==============> 1. Simple For loop Example.");
		for (int i = 0; i < tutorials.size(); i++) {
			//			System.out.println(tutorials.get(i));
			//			System.out.println(tutorials.get(i).getTutId());
			//			System.out.println(tutorials.get(i).getType());

			if(vendors.contains(tutorials.get(i).getAuthor())) {
				//Shouyd add to existin
				Product p = new Product();
				p.setDescription(tutorials.get(i).getDescription());
				p.setCur(tutorials.get(i).getType());
				p.setGtin(tutorials.get(i).getTutId());
				p.setSuppl(tutorials.get(i).getTitle());
				p.setPrice(tutorials.get(i).getDate());
				
				insertInVendorDocument(p,tutorials.get(i).getAuthor());
			
			}
			else {
				//should create new
				Product p = new Product();
				p.setDescription(tutorials.get(i).getDescription());
				p.setCur(tutorials.get(i).getType());
				p.setGtin(tutorials.get(i).getTutId());
				p.setSuppl(tutorials.get(i).getTitle());
				p.setPrice(tutorials.get(i).getDate());
				
				createNewVendorDocument(p,tutorials.get(i).getAuthor());
				vendors.add(tutorials.get(i).getAuthor());
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