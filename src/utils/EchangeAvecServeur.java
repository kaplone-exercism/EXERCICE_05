package utils;

import java.util.concurrent.CompletableFuture;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

public class EchangeAvecServeur {
	
	private AsyncHttpClient asyncHttpClient;
	private CompletableFuture<Response> reponse;
	private String user;
	private String pass;
	private String adresse;
	private String port;
	private String route;
	private String body;
	private String type;
	
	public EchangeAvecServeur(){
		 this.asyncHttpClient = new DefaultAsyncHttpClient();
		 SettingsServeur.readSettingServeur(this);
	}
	
	public CompletableFuture<Response> reponseGet(){
		
		reponse = asyncHttpClient
			     .prepareGet(String.format("%s:%s%s", adresse, port, route))
			     .execute()
			     .toCompletableFuture();
		
		return reponse;
	}
	
	public CompletableFuture<Response> reponsePost(){
		
		reponse = asyncHttpClient
			     .preparePost(String.format("%s:%s%s", adresse, port, route))
			     .addQueryParam("user", user)
			     .setBody(body)
			     .setHeader("Content-Type", type)
			     .execute()
			     .toCompletableFuture();
		
		return reponse;
	}



	public AsyncHttpClient getAsyncHttpClient() {
		return asyncHttpClient;
	}



	public void setAsyncHttpClient(AsyncHttpClient asyncHttpClient) {
		this.asyncHttpClient = asyncHttpClient;
	}



	public CompletableFuture<Response> getReponse() {
		return reponse;
	}



	public void setReponse(CompletableFuture<Response> reponse) {
		this.reponse = reponse;
	}



	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public String getPass() {
		return pass;
	}



	public void setPass(String pass) {
		this.pass = pass;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getPort() {
		return port;
	}



	public void setPort(String port) {
		this.port = port;
	}



	public String getRoute() {
		return route;
	}



	public void setRoute(String route) {
		this.route = route;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}
	
	

}
