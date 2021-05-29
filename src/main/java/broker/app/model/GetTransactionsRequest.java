package broker.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetTransactionsRequest {

	@JsonProperty(value = "username", required = true)
	private String username;
	@JsonProperty(value = "apiKey", required = true)
	private String apiKey;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public GetTransactionsRequest(String username, String apiKey) {
		super();
		this.username = username;
		this.apiKey = apiKey;
	}

	public GetTransactionsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
