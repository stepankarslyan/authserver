package hello;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import hello.Place;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceSearchResponse {
	
	@JsonProperty("results")
	private List<Place> results;

	public List<Place> getResults() {
		return results;
	}

	public void setResults(List<Place> results) {
		this.results = results;
	}
 
	
}
