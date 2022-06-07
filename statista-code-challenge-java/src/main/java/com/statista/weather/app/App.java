package com.statista.weather.app;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;


public class App {

    /**
     * Either create a simple console application
     * or a javaFx application which sources the DarkSky
     * API to retrieve the current weather information for the given
     * location on the planet.
     *
     * @param args
     */
    public static void main(String[] args) {
    	
    	WeatherDataImporter o_wdi = new WeatherDataImporter();
    	
    	Scanner o_inputScanner = new Scanner(System.in);
    	System.out.println("Hello, please enter the name of a city for which you would like to see current weather");
    		try {
    			String s_inputString = o_inputScanner.nextLine();
				o_wdi.loadWeatherData(s_inputString);
				System.out.print("The temperature in "+ s_inputString +" is "+ o_wdi.getTemperatureInCelsius() +"°C");
				System.out.println(" and the weather is "+ o_wdi.getWeatherConditions().toLowerCase());

			} catch (IOException | InterruptedException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	o_inputScanner.close();
    }

}
