package broker.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import broker.app.entity.BrokerHoldings;

@Repository
@Transactional
public interface BrokerHoldingsRepository extends JpaRepository<BrokerHoldings, Integer> {
	
	@Query(value = "SELECT HOLDINGS_ID, STOCK_ID, NUMBER_OF_STOCKS, AVERAGE_PRICE, BOUGHT_PRICE FROM BROKER_HOLDINGS WHERE BROKER_ID = :BROKER_ID", nativeQuery = true)
	public List<BrokerHoldings> getHoldingsUsingBrokerId(@Param(value = "BROKER_ID") int brokerId);
	
	@Query(value = "SELECT NUMBER_OF_STOCKS FROM BROKER_HOLDINGS WHERE BROKER_ID = :BROKER_ID AND STOCK_ID = :STOCK_ID", nativeQuery = true)
	public BrokerHoldings getStockInventoryUsingBrokerId(@Param(value = "BROKER_ID") int brokerId, @Param(value = "STOCK_ID") String stockId);
	
	@Modifying
	@Query(value = "UPDATE BROKER_HOLDINGS SET NUMBER_OF_STOCKS = :NUMBER_OF_STOCKS, AVERAGE_PRICE = :AVERAGE_PRICE, UPDATED_DATE = :UPDATED_DATE WHERE BROKER_ID = :BROKER_ID AND STOCK_ID = :STOCK_ID", nativeQuery = true)
	public void updateHoldings(@Param(value = "NUMBER_OF_STOCKS") int numberOfStocks, @Param(value = "AVERAGE_PRICE") float averagePrice, @Param(value = "UPDATED_DATE") LocalDateTime updatedDate, @Param(value = "BROKER_ID") int brokerID, @Param(value = "STOCK_ID") String stockId);
	
	@Modifying
	@Query(value = "INSERT INTO BROKER_HOLDINGS(NUMBER_OF_STOCKS, AVERAGE_PRICE, UPDATED_DATE, BROKER_ID, STOCK_ID) VALUES(:NUMBER_OF_STOCKS, :AVERAGE_PRICE, :UPDATED_DATE, :BROKER_ID, :STOCK_ID)", nativeQuery = true)
	public void newHoldings(@Param(value = "NUMBER_OF_STOCKS") int numberOfStocks, @Param(value = "AVERAGE_PRICE") float averagePrice, @Param(value = "UPDATED_DATE") LocalDateTime updatedDate, @Param(value = "BROKER_ID") int brokerID, @Param(value = "STOCK_ID") String stockId);
}
