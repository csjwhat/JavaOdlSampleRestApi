package jp.co.agilegroup.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class RestClient {

	private String account = null;
	private String password = null;

	public RestClient(String account, String password) {
		this.account = account;
		this.password = password;
	}

	private Client getClient() {
		Client client = new Client();
		client.addFilter(new HTTPBasicAuthFilter(account, password));
		return client;
	}

	public String get(String url, MediaType type) {
		Client client = getClient();

		WebResource resource;
		ClientResponse response = null;
		try {
			resource = client.resource(url);
			response = resource.accept(type).get(ClientResponse.class);

			switch (response.getStatus()) {
			case 200: // OK
				break;

			default:
				return String.format("Code:%s Entity:%s", response.getStatus(), response.getEntity(String.class));
			}
		} catch (Throwable e) {
			new Throwable(e);
		}

		return response.getEntity(String.class);
	}


	public void delete(String url) {
		Client client = getClient();

		ClientRequest.Builder builder = ClientRequest.create();
		try {
			ClientRequest request = builder.build(new URI(url), HttpMethod.DELETE);
			ClientResponse response = client.handle(request);

			if (response.getStatus() != 204 && response.getStatus() != 200 ) {
				// サイトでは204が成功のステータスとされているが、今回 200で飛んできた
				String error = response.getEntity(String.class);
				throw new RuntimeException(error);
			}
		} catch (URISyntaxException e) {
			new RuntimeException(e);
		}
	}

	public <E> String post(String uri, E entity, Class<E> cls, MediaType type) {
		return sendRequest(uri, entity, HttpMethod.POST, cls, type);
	}

	public <E> String put(String uri, E entity, Class<E> cls, MediaType type) {
		return sendRequest(uri, entity, HttpMethod.PUT, cls, type);
	}

	private <E> String sendRequest(String uri, E entity, String method, Class<E> cls, MediaType type) {
		Client client = getClient();
		ClientRequest.Builder builder = ClientRequest.create();

		try {
			builder.type(type).entity(entity);
			ClientRequest request = builder.build(new URI(uri), method);
			ClientResponse response = client.handle(request);

			switch (response.getStatus()) {
			case 200: // OK
			case 201: // CREATED
				return response.getEntity(String.class);

			default:
				String error = response.getEntity(String.class);
				throw new RuntimeException(error);
			}

		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
