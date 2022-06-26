package avaas.openweathermap;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class OpenWeatherMapAPI {
	
	private static final String API_KEY = "25644b338c6001649904aee9f3d23a70";
	
	public static String getWeatherData(String latitude, String longitude) {
		
		ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
		StringBuffer sb = new StringBuffer();
		
		sb.append("https://api.openweathermap.org/data/2.5/weather?lat=");
		sb.append(latitude);
		sb.append("&lon=");
		sb.append(longitude);
		sb.append("&appid=");
		sb.append(API_KEY);
		
		ResteasyWebTarget target = client.target(sb.toString());
		Response response = target.request().get();
		String weatherData = response.readEntity(String.class);
		response.close();
		
//		System.out.println(weatherData);
		
		return weatherData; 
	}
	
	public static String processWeatherData(String weatherData) {
		
		String[] msgArray = weatherData.replace("{\"coord\":{\"lon\":", "").replace("\"lat\":", "")
				.replace("}", "").replace("{", "").replace("\"weather\":[\"id\":", "").replace("\"main\":", "")
				.replace("\"description\":", "").replace("\"", "").split(",");

		String mainWeather = msgArray[3];
		String description = msgArray[4];
		
		return mainWeather + "," + description; 
	}
}
