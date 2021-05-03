package com.solarIrradiance.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

public class FetchDataWithHttpClientService implements IFetchDataService {

	public FetchDataWithHttpClientService() {
	}
	
	
	
	public String fetchData(String p_host, List<NameValuePair> p_params) {

		String result = null;
		try {		
			URI uri = generateUrI(p_host, p_params);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request;
			request = HttpRequest
						.newBuilder()
						.uri(uri)
						.header("accept", "application/json")
						.build();
		
			CompletableFuture<HttpResponse<String>> response = client.sendAsync(request,
				HttpResponse.BodyHandlers.ofString());

			result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
			
			
			
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	private URI generateUrI(String p_host, List<NameValuePair> p_params) {
		URI uri = null;
		try {
			URIBuilder builder = new URIBuilder();
			builder.setScheme("https");
			builder.setHost(p_host);
			builder.addParameters(p_params);
			uri = builder.build();
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		return uri;
	}
}
