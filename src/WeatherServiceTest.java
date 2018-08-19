import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the WeatherService class. Creates mock data that simulates the data 
 * used in this app that is provided by the JSON response from OpenWeatherMap.org. Test
 * cases provided for the method that binds JSON data to java objects and for the sort
 * function.
 * 
 * @author Michael Koprowski
 */
public class WeatherServiceTest {
	private WeatherService ws;
	private JsonArray arr;
	private Weather w1, w2, w3;

	/**
	 * Set up mock JSON data that simulates the data that would be received from 
	 * OpenWeatherMap.org and the expected data after the app parses the JSON data.
	 */
	@Before
	public void setUp() throws Exception {
		ws = new WeatherService();

		List<WeatherStatus> list1 = new ArrayList<WeatherStatus>();
		List<WeatherStatus> list2 = new ArrayList<WeatherStatus>();
		List<WeatherStatus> list3 = new ArrayList<WeatherStatus>();

		list1.add(new WeatherStatus("Cloudy", "Lots of clouds"));
		list2.add(new WeatherStatus("Rain", "Cats and Dogs"));
		list3.add(new WeatherStatus("Clear", "Not a cloud in sight"));

		JsonArrayBuilder arrBld = Json.createArrayBuilder();

		JsonObject obj1 = Json.createObjectBuilder()
				.add("name", "Saint Francis")
				.add("main", Json.createObjectBuilder()
						.add("temp", 99.9)
						.add("pressure", 400)
						.add("humidity", 30)
						.build())
				.add("wind", Json.createObjectBuilder()
						.add("speed", 25)
						.add("deg", 15)
						.build())
				.add("weather", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("main", "Cloudy")
								.add("description", "Lots of clouds")
								.build())
						.build())
				.build();

		JsonObject obj2 = Json.createObjectBuilder()
				.add("name", "Milwaukee")
				.add("main", Json.createObjectBuilder()
						.add("temp", 75.0)
						.add("pressure", 10)
						.add("humidity", 70)
						.build())
				.add("wind", Json.createObjectBuilder()
						.add("speed", 10)
						.add("deg", 75)
						.build())
				.add("weather", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("main", "Rain")
								.add("description", "Cats and Dogs")
								.build())
						.build())
				.build();

		JsonObject obj3 = Json.createObjectBuilder()
				.add("name", "Shorewood")
				.add("main", Json.createObjectBuilder()
						.add("temp", 75.1)
						.add("pressure", 300)
						.add("humidity", 10)
						.build())
				.add("wind", Json.createObjectBuilder()
						.add("speed", 50)
						.add("deg", 5)
						.build())
				.add("weather", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("main", "Clear")
								.add("description", "Not a cloud in sight")
								.build())
						.build())			
				.build();

		w1 = new Weather("Saint Francis", 
				new Temperature(99.9, 400, 30),
				new Wind(25, 15), list1);
		w2 = new Weather("Milwaukee", 
				new Temperature(75.0, 10, 70),
				new Wind(10, 75), list2);
		w3 = new Weather("Shorewood", 
				new Temperature(75.1, 300, 10),
				new Wind(50, 5), list3);

		arr = arrBld.add(obj1)
				.add(obj2)
				.add(obj3)
				.build();
	}

	/**
	 * Tests whether JSON data is parsed to the java objects as expected.
	 */
	
	@Test
	public void testBindJsonToWeather() {
		List<Weather> weatherList = ws.bindJsonToWeather(arr);

		assertEquals(w1, weatherList.get(0));
		assertEquals(w2, weatherList.get(1));
		assertEquals(w3, weatherList.get(2));
	}

	/**
	 * Tests whether the merge sort function correctly sorts the weather data
	 * according to increasing temperature values.
	 */
	@Test
	public void testSortTemp() {
		ws.bindJsonToWeather(arr);
		ws.sortTemp();
		List<Weather> weatherList = ws.getWeatherList();
		
		assertEquals(w2, weatherList.get(0));
		assertEquals(w3, weatherList.get(1));
		assertEquals(w1, weatherList.get(2));
	}

}
