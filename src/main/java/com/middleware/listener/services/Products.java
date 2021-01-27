package com.middleware.listener.services;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Products {

	private List<Product> product;

	public List<Product> getProduct() {
		return product;
	}


	public void setProduct(List<Product> product) {
		this.product = product;
	}
}