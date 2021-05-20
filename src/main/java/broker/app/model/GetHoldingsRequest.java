package broker.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetHoldingsRequest {

	@JsonProperty(value = "username", required = true)
	private String username;
	@JsonProperty(value = "apiKey", required = true)
	private String apikey;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public GetHoldingsRequest(String username, String apikey) {
		super();
		this.username = username;
		this.apikey = apikey;
	}

	public GetHoldingsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
