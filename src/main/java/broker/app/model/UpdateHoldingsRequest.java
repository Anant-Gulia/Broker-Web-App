package broker.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateHoldingsRequest {

	@JsonProperty(value = "username", required = true)
	private String username;
	@JsonProperty(value = "stockId", required = true)
	private String stockId;
	@JsonProperty(value = "numberOfStocks", required = true)
	private Integer numberOfStocks;
	@JsonProperty(value = "aipKey", required = true)
	private String apiKey;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public Integer getNumberOfStocks() {
		return numberOfStocks;
	}

	public void setNumberOfStocks(Integer numberOfStocks) {
		this.numberOfStocks = numberOfStocks;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public UpdateHoldingsRequest(String username, String stockId, Integer numberOfStocks, String apiKey) {
		super();
		this.username = username;
		this.stockId = stockId;
		this.numberOfStocks = numberOfStocks;
		this.apiKey = apiKey;
	}

	public UpdateHoldingsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
