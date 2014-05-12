/*
 * devjav [http://devjav.com]
 * Copyright (C) 2014-2014 Pham Thai Thinh
 * Contact:phamthaithinh@gmail.com
 * 
 */
package com.devjav.jersey;

/**
 * 
 * @author Pham Thai Thinh
 * 
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RestClient client = RestClient.getInstance();
		User user = new User();
		user.setId(1l);
		user.setName("ThinhPT");
		client.createUser(user);
		user=client.getUser(1l);
		user.setName("ThinhPT new");
		client.updateUser(user);
	}

}
