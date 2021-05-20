package broker.app.model;

import java.util.List;

import broker.app.entity.BrokerHoldings;

public class GetHoldingsResponse {

	private boolean error;
	private String errorMessage;
	private String username;
	private List<BrokerHoldings> holdings;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<BrokerHoldings> getHoldings() {
		return holdings;
	}

	public void setHoldings(List<BrokerHoldings> holdings) {
		this.holdings = holdings;
	}

	public GetHoldingsResponse(boolean error, String errorMessage, String username, List<BrokerHoldings> holdings) {
		super();
		this.error = error;
		this.errorMessage = errorMessage;
		this.username = username;
		this.holdings = holdings;
	}

	public GetHoldingsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
