package com.middleware.listener.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
	
	public static void convertStringToTimestamp(String strDate) {
		try {
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS");
			LocalDateTime dateTime = LocalDateTime.parse(strDate, formatter);
			System.out.println(dateTime);	

		}catch (Exception e) {System.out.println(e);};	
	}
}
