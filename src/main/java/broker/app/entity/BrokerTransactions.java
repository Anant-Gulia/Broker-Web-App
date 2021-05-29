package broker.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BROKER_TRANSCATIONS")
public class BrokerTransactions {

	@Id
	@Column(name = "TRANSACTION_ID")
	private Integer holdingsId;
	@Column(name = "STOCK_ID")
	private String stockId;
	@Column(name = "STOCK_AMOUNT")
	private Integer stockAmount;
	@Column(name = "TOTAL_SPENT")
	private Float totalSpent;
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	@Column(name = "TRANSACTION_TYPE")
	private String transactionType;

	public Integer getHoldingsId() {
		return holdingsId;
	}

	public void setHoldingsId(Integer holdingsId) {
		this.holdingsId = holdingsId;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public Integer getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(Integer stockAmount) {
		this.stockAmount = stockAmount;
	}

	public Float getTotalSpent() {
		return totalSpent;
	}

	public void setTotalSpent(Float totalSpent) {
		this.totalSpent = totalSpent;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BrokerTransactions(Integer holdingsId, String stockId, Integer stockAmount, Float totalSpent,
			LocalDateTime createdDate, String transactionType) {
		super();
		this.holdingsId = holdingsId;
		this.stockId = stockId;
		this.stockAmount = stockAmount;
		this.totalSpent = totalSpent;
		this.createdDate = createdDate;
		this.transactionType = transactionType;
	}

	public BrokerTransactions() {
		super();
		// TODO Auto-generated constructor stub
	}

}
