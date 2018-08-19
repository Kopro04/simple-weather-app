/**
 * Weather application that fetches weather data from OpenWeatherMap.org, sorts it in 
 * ascending order by temperature and prints the info to the console
 * 
 * @author Michael Koprowski
 */

public class SimpleWeatherApp {
	public static void main(String[] args) {
		WeatherService weather = new WeatherService();
		weather.fetchWeather();
		weather.sortTemp();
		System.out.println(weather);
	}
}
