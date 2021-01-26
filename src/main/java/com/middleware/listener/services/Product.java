package com.middleware.listener.services;




import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Product {



	private String description;
	private String gtin;
	private String price;
	
	
	public String getCur() {
		return cur;
	}

	@XmlAttribute(name= "currency")
	public void setCur(String cur) {
		this.cur = cur;
	}

	private String cur;
	private String suppl;



	public String getDescription() {
		return description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	public String getGtin() {
		return gtin;
	}

	@XmlElement
	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public String getPrice() {
		return price;
	}

	@XmlElement(name="price")
	public void setPrice(String price) {
		this.price = price;
	}

	public String getSuppl() {
		return suppl;
	}

	@XmlElement(name="supplier")
	public void setSuppl(String suppl) {
		this.suppl = suppl;
	}



}