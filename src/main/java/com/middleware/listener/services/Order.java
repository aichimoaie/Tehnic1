package com.middleware.listener.services;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;




public class Order implements  Comparable<Order>{
	private String created;
	private String ID;
	
	private List<Product> product;
	
	@XmlElement(name="product")	
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public String getCreated() {
		return created;
	}

	@XmlAttribute(name="created")
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
	

	  public Date getCreatedOn() {
	    return Util.convertStringToTimestamp(created);
	  }

//	  public void setCreatedOn(Date createdOn) {
//	    this.created = createdOn;
//	  }

	  @Override
	  public int compareTo(Order o) {
	    if (getCreatedOn() == null || o.getCreatedOn() == null) {
	      return 0;
	    }
	    return getCreatedOn().compareTo(o.getCreatedOn());
	  }

}
