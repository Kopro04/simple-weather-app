import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO that provides the structure for temperature data
 * 
 * @author Michael Koprowski
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature {
	private double temp;	// temperature in degrees F
	private int pressure;	// air pressure in hPa
	private int humidity;	// humidity in %
	
	public Temperature() {
		super();
	}
	
	public Temperature(double temp, int pressure, int humidity) {
		this.temp = temp;
		this.pressure = pressure;
		this.humidity = humidity;
	}
	
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public int getPressure() {
		return pressure;
	}
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	
	/**
	 * Creates a string representation of temperature data
	 * 
	 * @return String representation of temperature data
	 */
	
	@Override
	public String toString() {
		String s = "\n         Temperature (\u00b0F): " + temp;
		s += "\n         Pressure (hPa): " + pressure;
		s += "\n         Humidity (%): " + humidity;
		
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		if(!(obj instanceof Temperature)) return false;
		
		Temperature other = (Temperature) obj;
		if(this.temp != other.temp || 
				this.pressure != other.pressure||
				this.humidity !=other.humidity)
			return false;
		return true;
	}
}
