package com.middleware.listener.services;


import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class Help {


	public static void main(String[] args) {
		

		File a = new File("/home/bogdan/Documents/Bucharest/webapi/src/main/resources/static/orders24.xml");
		JaxbParser jax = new JaxbParser(a);
		jax.splitOrderFile();
	}


}
