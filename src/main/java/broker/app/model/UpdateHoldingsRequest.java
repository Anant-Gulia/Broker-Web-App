package broker.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateHoldingsRequest {

	@JsonProperty(value = "username", required = true)
	private String username;
	@JsonProperty(value = "stockId", required = true)
	private String stockId;
	@JsonProperty(value = "numberOfStocks", required = true)
	private Integer numberOfStocks;

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

	public void setNumberOfStocks(Integer number_of_stocks) {
		this.numberOfStocks = number_of_stocks;
	}

	public UpdateHoldingsRequest(String username, String stockId, Integer number_of_stocks) {
		super();
		this.username = username;
		this.stockId = stockId;
		this.numberOfStocks = number_of_stocks;
	}

	public UpdateHoldingsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
