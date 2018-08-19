import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO that provides the structure for weather data
 * 
 * @author Michael Koprowski
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
	@JsonProperty("name")
	private String city;	// City name

	@JsonProperty("main")
	private Temperature temperature;	// Temperature object

	@JsonProperty("wind")
	private Wind wind;	// Wind object

	@JsonProperty("weather")
	private List<WeatherStatus> status;	// List of weather status info
	
	public Weather() {
		super();
	}
	
	public Weather(String city, Temperature temperature, 
			Wind wind, List<WeatherStatus> status) {
		this.city = city;
		this.temperature = temperature;
		this.wind = wind;
		this.status = status;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Temperature getTemperature() {
		return temperature;
	}

	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public List<WeatherStatus> getStatus() {
		return status;
	}

	public void setStatus(List<WeatherStatus> status) {
		this.status = status;
	}
	
	/**
	 * Creates a string representation of Weather data
	 * 
	 * @return String representation of Weather data
	 */
	
	@Override
	public String toString() {
		String s = "City: " + city;
		s += "\n     Temperature info: " + temperature;
		s += "\n     Wind info: " + wind;
		s += "\n     Weather Status: " + status + "\n";
		
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		if(!(obj instanceof Weather)) return false;
		
		Weather other = (Weather) obj;
		if(!this.city.equals(other.city) || 
				!this.temperature.equals(other.temperature)||
				!this.wind.equals(other.wind) || 
				!this.status.equals(other.status))
			return false;
		return true;
	}
}
