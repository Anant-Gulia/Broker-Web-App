package broker.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BROKER_HOLDINGS")
public class BrokerHoldings {

	@Id
	@Column(name = "HOLDINGS_ID")
	private Integer holdingsId;
	@Column(name = "STOCK_ID")
	private String stockId;
	@Column(name = "NUMBER_OF_STOCKS")
	private Integer numberOfStocks;
	@Column(name = "AVERAGE_PRICE")
	private Float averagePrice;

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

	public Integer getNumberOfStocks() {
		return numberOfStocks;
	}

	public void setNumberOfStocks(Integer numberOfStocks) {
		this.numberOfStocks = numberOfStocks;
	}

	public Float getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(Float averagePrice) {
		this.averagePrice = averagePrice;
	}

	public BrokerHoldings(Integer holdingsId, String stockId, Integer numberOfStocks, Float averagePrice) {
		super();
		this.holdingsId = holdingsId;
		this.stockId = stockId;
		this.numberOfStocks = numberOfStocks;
		this.averagePrice = averagePrice;
	}

	public BrokerHoldings() {
		super();
		// TODO Auto-generated constructor stub
	}

}
