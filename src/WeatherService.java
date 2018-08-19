import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service that retrieves JSON weather data from OpenWeatherMap.org, parses it to
 * java objects and sorts the weather data
 * 
 * @author Michael Koprowski
 */
public class WeatherService {
	private List<Weather> weatherList;
	private Weather[] temp;

	public WeatherService() {
		weatherList = new ArrayList<Weather>();
	}

	public List<Weather> getWeatherList(){
		return weatherList;
	}

	public void setWeatherList(List<Weather> weatherList) {
		this.weatherList = weatherList;
	}

	/**
	 * Contacts the OpenWeatherMap.org API and requests JSON weather data
	 * 
	 * @return List of Weather objects
	 */
	
	public List<Weather> fetchWeather(){
		double milwaukeeLat = 43.0;
		double milwaukeeLon = -87.9;

		String url = createURL(milwaukeeLat, milwaukeeLon);

		// Create the client that consumes the API
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		Response response = target.request(MediaType.APPLICATION_JSON).get();

		// Package the list of names into a JsonArray
		JsonArray data = response.readEntity(JsonObject.class).getJsonArray("list");

		response.close();
		client.close();

		return bindJsonToWeather(data);
	}
	
	/**
	 * Creates the URL used to get weather data from OpenWeatherMap.org from the 10
	 * cities closest to the provided coordinates
	 * 
	 * @param lat The latitude coordinate
	 * @param lon The longitude coordinate
	 * @return String representation of the URL that requests the desired weather data
	 */

	private String createURL(double lat, double lon) {
		String url = "http://api.openweathermap.org/data/2.5/find?lat=";
		String apiKey = "84dde353cf2757e657ef9075598771c2";

		return url + lat + "&lon=" + lon +
				"&units=imperial&cnt=10&APPID=" + apiKey;
	}
	
	/**
	 * Parses JSON data into java Weather objects
	 * 
	 * @param weatherData Array of JSON objects from OpenWeatherMap.org
	 * @return A list of Weather objects
	 */

	protected List<Weather> bindJsonToWeather(JsonArray weatherData){
		System.out.println("Source Weather Data: " + weatherData);

		ObjectMapper mapper = new ObjectMapper();

		try{
			for(int i = 0; i < weatherData.size(); i++) {
				weatherList.add(mapper.readValue(
						weatherData.get(i).toString(), Weather.class));
			}
		}catch(JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return weatherList;
	}
	
	/**
	 * Sorts the list of weather data in ascending order by temperature. Uses a merge 
	 * sort algorithm.
	 */

	public void sortTemp() {
		temp = new Weather[weatherList.size()];
		mergeSort(0, weatherList.size() - 1);
	}
	
	/**
	 * Helper recursive merge sort method that manages the sorting of Weather objects
	 * 
	 * @param lowerIndex The lower index of the List/sub-List being sorted
	 * @param upperIndex The upper index of the List/sub-List being sorted
	 */

	private void mergeSort(int lowerIndex, int upperIndex) {
		if(lowerIndex < upperIndex) {
			int middleIndex = lowerIndex + (upperIndex - lowerIndex) / 2;
			mergeSort(lowerIndex, middleIndex);
			mergeSort(middleIndex +1, upperIndex);
			merge(lowerIndex, middleIndex, upperIndex);
		}
	}
	
	/**
	 * Helper merge sort method that actually sorts Weather objects in the List
	 * 
	 * @param lowerIndex The lower index of the List/sub-List being sorted
	 * @param middleIndex The middle index of the List/sub-List being sorted
	 * @param upperIndex The upper index of the List/sub-List being sorted
	 */

	private void merge(int lowerIndex, int middleIndex, int upperIndex) {
		for(int i = lowerIndex; i <= upperIndex; i++) {
			temp[i] = weatherList.get(i);
		}	

		int i = lowerIndex;
		int j = middleIndex + 1;
		int k = lowerIndex;

		while(i <= middleIndex && j <= upperIndex) {
			if(temp[i].getTemperature().getTemp() <= temp[j].getTemperature().getTemp()) {
				weatherList.set(k, temp[i]);
				i++;
			}
			else {
				weatherList.set(k, temp[j]);
				j++;
			}
			k++;
		}

		while(i <= middleIndex) {
			weatherList.set(k, temp[i]);
			i++;
			k++;
		}

		while(j <= upperIndex) {
			weatherList.set(k, temp[j]);
			j++;
			k++;
		}
	}
	
	/**
	 * Creates a string representation of weatherList
	 * 
	 * @return String representation of weatherList
	 */

	@Override
	public String toString() {
		String s = "";

		for(int i = 0; i < weatherList.size(); i++) {
			s += "\n" + (i + 1) + ".  " + weatherList.get(i);
		}

		return s;
	}
}
