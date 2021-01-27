package com.middleware.listener.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Util {

	public static Date convertStringToTimestamp(String strDate) {

		DateTimeFormatter formatter= DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime dateTime = LocalDateTime.parse(strDate, formatter);
		System.out.println(dateTime);
		return java.util.Date
				.from(dateTime.atZone(ZoneId.systemDefault())
						.toInstant());




	}
}
