package broker.app.model;

import java.util.List;

import broker.app.entity.BrokerTransactions;

public class GetTransactionsResponse {

	private boolean error;
	private String errorMessage;
	private String username;
	private List<BrokerTransactions> transactions;

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

	public List<BrokerTransactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<BrokerTransactions> transactions) {
		this.transactions = transactions;
	}

	public GetTransactionsResponse(boolean error, String errorMessage, String username,
			List<BrokerTransactions> transactions) {
		super();
		this.error = error;
		this.errorMessage = errorMessage;
		this.username = username;
		this.transactions = transactions;
	}

	public GetTransactionsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
