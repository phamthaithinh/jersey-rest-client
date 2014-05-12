/*
 * devjav [http://devjav.com]
 * Copyright (C) 2014-2014 Pham Thai Thinh
 * Contact:phamthaithinh@gmail.com
 * 
 */
package com.devjav.jersey;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * 
 * @author Pham Thai Thinh
 * 
 */
public class RestClient {
	private static Client client;
	private static RestClient instance;
	private static final String TARGET = "http://localhost:8080/jersey/services";

	private RestClient() {
		ClientConfig clientConfig = new ClientConfig();
		LoggingFilter loggingFilter = new LoggingFilter(
				Logger.getLogger("restapi"), true);
		client = ClientBuilder.newClient(clientConfig).register(
				JacksonFeature.class);
		client = client.register(loggingFilter);
	}

	public static synchronized RestClient getInstance() {
		if (instance == null) {
			instance = new RestClient();
		}
		return instance;
	}

	public User createUser(User bean) {
		WebTarget webTarget = client.target(TARGET);
		webTarget = webTarget.path("user");
		Invocation.Builder builder = webTarget.request(
				MediaType.APPLICATION_JSON_TYPE);
		User user = builder.post(
				Entity.entity(bean, MediaType.APPLICATION_JSON_TYPE),
				User.class);
		return user;

	}

	public User getUser(Long id) {
		WebTarget webTarget = client.target(TARGET);
		webTarget = webTarget.path("user");
		webTarget = webTarget.path("{id}").resolveTemplate("id", id);
		Invocation.Builder builder = webTarget.request(
				MediaType.APPLICATION_JSON_TYPE);

		User user = builder.get(User.class);
		return user;
	}

	public User updateUser(User bean) {
		WebTarget webTarget = client.target(TARGET);
		webTarget = webTarget.path("user");
		Invocation.Builder builder = webTarget.request(
				MediaType.APPLICATION_JSON_TYPE);

		User user = builder.put(
				Entity.entity(bean, MediaType.APPLICATION_JSON_TYPE),
				User.class);
		return user;
	}

}
