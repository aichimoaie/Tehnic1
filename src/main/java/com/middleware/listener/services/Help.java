package com.middleware.listener.services;


import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class Help {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Util u = new Util();
		//u.convertStringToTimestamp("2012-07-12T15:29:33.000");
		File a = new File("/home/bogdan/Documents/Bucharest/webapi/src/main/resources/static/orders23.xml");

		JaxbParser jax = new JaxbParser(a);
		jax.splitOrderFile();
	}


}
