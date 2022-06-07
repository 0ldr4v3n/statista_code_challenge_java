package com.statista.weather.app;

//import com.weatherapi.api.http.request.HttpRequest;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * imported data set looks roughly like this:
	last_updated_epoch:1654571700,
	last_updated:2022-06-07 05:15,
	temp_c:13.0,
	temp_f:55.4,
	is_day:1,
	condition:{
		text:Partly cloudy,
		icon://cdn.weatherapi.com/weather/64x64/day/116.png,
		code:1003},
	wind_mph:6.9,
	wind_kph:11.2,
	wind_degree:200,
	wind_dir:SSW,
	pressure_mb:1014.0,
	pressure_in:29.94,
	precip_mm:0.0,
	precip_in:0.0,
	humidity:77,
	cloud:50,
	feelslike_c:12.8,
	feelslike_f:55.0,
	vis_km:10.0,
	vis_miles:6.0,
	uv:1.0,
	gust_mph:8.5,
	gust_kph:13.7
 * 
 */


public class WeatherDataImporter {
    private static String s_baseUri = "https://api.weatherapi.com/v1";
    private static String s_key = "0a05194c892f4d17a07194508220606";
    private JSONObject o_jsonWeatherObj;
    
	public WeatherDataImporter(){}
	
	/*
	 * build URL Request for current weather
	 * @param string cityName
	 * @return string URL as a String  
	 */
	private String _buildCurrentWeatherRequest(String s_cityName) {
		String s_urlRequest = ""+ s_baseUri+"/current.json?key="+ s_key +"&q="+ s_cityName +"&aqi=no";  	
		return s_urlRequest;
	}
	
	
	/*
	 * load weather data into WeatherDataImporter.o_jsonWeatherObj
	 * @param string s_cityName city name
	 * @return void
	 */
	public void loadWeatherData( String s_cityName) throws IOException, InterruptedException, ParseException {
		
		Object o_weatherObj;
		HttpClient o_client = HttpClient.newHttpClient();
		HttpRequest o_request = HttpRequest.newBuilder()
				.uri(URI.create(_buildCurrentWeatherRequest(s_cityName)))
				.build();
		HttpResponse<String> o_response = o_client.send(o_request, BodyHandlers.ofString());
		if(o_response != null) {
		
			JSONParser o_jsonParser = new JSONParser();
			String s_responseBody = o_response.body();
			//String manipulation to make result readable
			s_responseBody = s_responseBody.split("current\":")[1];
			s_responseBody = s_responseBody.substring(0, s_responseBody.length()-1);
			o_weatherObj = o_jsonParser.parse(s_responseBody);
			
			this.o_jsonWeatherObj = (JSONObject) o_weatherObj; 
		}
	}
	
	public JSONObject getJsonWeatherData() {
		return this.o_jsonWeatherObj;
	}
	public double getTemperatureInCelsius() {
		if( o_jsonWeatherObj == null) { 
			System.out.println("Error, Weather data is not loaded");
			return 15000000.0;
		}
		
		return (double) o_jsonWeatherObj.get("temp_c");
	}
	
	public String getWeatherConditions () {
		if( o_jsonWeatherObj == null) { 
			System.out.println("Error, weather data is not loaded");
			return "weather could not be loaded";
		}
		JSONObject o_condition = (JSONObject) o_jsonWeatherObj.get( "condition");
		
		return (String) o_condition.get("text");
	}
    
}
