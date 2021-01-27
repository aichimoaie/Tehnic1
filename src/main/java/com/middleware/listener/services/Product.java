package com.middleware.listener.services;
import javax.xml.bind.annotation.*;




@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Cloneable {

	private String description;
	private String gtin;
	//private String price;
	private String supplier;
	private Price price;
	
	
	
	public String getOrderID() {
		return orderID;
	}


	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	private String orderID;



	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public String getPrice() {
		return price.getValue();
	}


	public void setPrice(String price) {
		this.price.setValue(price);
	}

	public String getCurrency() {
		return price.getCurrency();
	}


	public void setCurrency(String currency) {
		this.price.setCurrency(currency);
	}

	public String getSuppl() {
		return supplier;
	}

	public void setSuppl(String suppl) {
		this.supplier = suppl;
	}
	
	@Override
	public String toString() {
		return "Product [description=" + description + ", gtin=" + gtin + ", supplier=" + supplier + ", orderID="
				+ orderID + ", getPrice()=" + getPrice() + ", getCurrency()=" + getCurrency() + "]";
	}


	public Object clone() throws
	CloneNotSupportedException 
	{ 
		return super.clone(); 
	} 

}