package com.statista.weather.app;

/**
 * Please add useful methods to this interface to retrieve weather information
 */
public interface IWeatherService {
	public int getTemp_f();
	public boolean getIs_day();
	public float getWind_mph();
	public float getWind_kph();
	public int getWind_degree();
	public String getWind_dir();
	public float getPressure_mb();
	public float getPressure_in();
	public float getPrecip_mm();
	public float getPrecip_in();
	public int getHumidity();
	public int getCloud();
	public float getFeelslike_c();
	public float getFeelslike_f();
	public float getVis_km();
	public float getVis_miles();
	public float getUv();
	public float getGust_mph();
	public float getGust_kph(); 
	
}
