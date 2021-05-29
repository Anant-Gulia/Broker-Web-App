package broker.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import broker.app.entity.BrokerTransactions;


@Repository
@Transactional
public interface BrokerTransactionsRepository extends JpaRepository<BrokerTransactions, Integer>{
	
	@Query(value = "SELECT * FROM BROKER_TRANSACTIONS WHERE BROKER_ID = :BROKER_ID", nativeQuery = true)
	public List<BrokerTransactions> getTransactionsUsingBrokerId (@Param(value = "BROKER_ID") Integer brokerId);
	
	@Modifying
	@Query(value = "INSERT INTO BROKER_TRANSACTIONS(BROKER_ID, STOCK_ID, STOCK_AMOUNT, TOTAL_SPENT, CREATED_DATE, TRANSACTION_TYPE) VALUES(:BROKER_ID, :STOCK_ID, :STOCK_AMOUNT, :TOTAL_SPENT, :CREATED_DATE, :TRANSACTION_TYPE)", nativeQuery = true)
	public void addTransaction (@Param(value = "BROKER_ID") Integer brokerId, @Param(value = "STOCK_ID") String stockId, @Param(value = "STOCK_AMOUNT") Integer stockAmount, @Param(value = "TOTAL_SPENT") Float totalSpent, @Param(value = "CREATED_DATE") LocalDateTime createdDate, @Param(value = "TRANSACTION_TYPE") String transactionType);
}
