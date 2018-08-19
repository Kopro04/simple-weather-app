import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO that provides the structure for wind data
 * 
 * @author Michael Koprowski
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind {
	@JsonProperty("speed")
	private int windSpd;  // wind speed in miles per hour
	
	@JsonProperty("deg")
	private int windDir; // wind direction in degrees
	
	public Wind() {
		super();
	}

	public Wind(int windSpd, int windDir) {
		this.windSpd = windSpd;
		this.windDir = windDir;
	}
	
	public int getWindSpd() {
		return windSpd;
	}

	public void setWindSpd(int windSpd) {
		this.windSpd = windSpd;
	}

	public int getWindDir() {
		return windDir;
	}

	public void setWindDir(int windDir) {
		this.windDir = windDir;
	}
	
	/**
	 * Creates a string representation of wind data
	 * 
	 * @return String representation of wind data
	 */
	
	@Override
	public String toString() {
		String s = "\n         Wind Speed (mph): " + windSpd;
		s += "\n         Wind Direction (\u00b0): " + windDir;
		
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		if(!(obj instanceof Wind)) return false;
		
		Wind other = (Wind) obj;
		if(this.windSpd != other.windSpd || this.windDir != other.windDir)
			return false;
		return true;
	}
}
