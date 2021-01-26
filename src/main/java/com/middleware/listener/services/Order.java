package com.middleware.listener.services;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="order")
public class Order {

	private List<Product> product;


	private String created;

	private String ID;

	public String getCreated() {
		return created;
	}

	@XmlAttribute
	public void setCreated(String created) {
		this.created = created;
	}

	public String getID() {
		return this.ID;
	}

	@XmlAttribute(name="ID")
	public void setID(String id_product) {
		this.ID = id_product;
	}


	@XmlElement(name="product")
	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}


}
