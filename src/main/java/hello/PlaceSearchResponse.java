package hello;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import hello.GooglePlace;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceSearchResponse {
	
	@JsonProperty("results")
	private List<GooglePlace> results;

	public List<GooglePlace> getResults() {
		return results;
	}

	public void setResults(List<GooglePlace> results) {
		this.results = results;
	}
 
	
}
