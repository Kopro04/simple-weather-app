import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO that provides the structure for weather status data
 * 
 * @author Michael Koprowski
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherStatus {
	@JsonProperty("main")
	private String status;	// The weather status
	
	@JsonProperty("description")
	private String statusDesc;	// Description of the weather status
	
	public WeatherStatus() {
		super();
	}

	public WeatherStatus(String status, String statusDesc) {
		this.status = status;
		this.statusDesc = statusDesc;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	/**
	 * Creates a string representation of the weather status data
	 * 
	 * @return String representation of the weather status data
	 */
	
	@Override
	public String toString() {
		String s = "\n         Status: " + status;
		s += "\n         Description: " + statusDesc;
		
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		if(!(obj instanceof WeatherStatus)) return false;
		
		WeatherStatus other = (WeatherStatus) obj;
		if(!this.status.equals(other.status) || 
				!this.statusDesc.equals(other.statusDesc))
			return false;
		return true;
	}
}
