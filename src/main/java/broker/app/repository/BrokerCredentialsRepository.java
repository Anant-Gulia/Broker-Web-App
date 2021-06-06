package broker.app.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import broker.app.entity.BrokerCredentials;

@Repository
@Transactional
public interface BrokerCredentialsRepository extends JpaRepository<BrokerCredentials, Integer> {

	@Query(value = "SELECT * FROM BROKER_CREDENTIALS WHERE USERNAME = :USERNAME", nativeQuery = true)
	public BrokerCredentials getCredentialsUsingUsername(@Param(value = "USERNAME") String username);
	
	@Modifying
	@Query(value = "UPDATE BROKER_CREDENTIALS SET API_KEY = :APIKEY, UPDATED_DATE = :UPDATED_DATE WHERE BROKER_CREDENTIALS_ID = :BROKER_CREDENTIALS_ID", nativeQuery = true)
	public void saveApiKey(@Param(value = "APIKEY") String apikey, @Param(value = "UPDATED_DATE") LocalDateTime updatedDate, @Param(value = "BROKER_CREDENTIALS_ID") int brokerCredentialsId);

	@Modifying
	@Query(value = "UPDATE BROKER_CREDENTIALS SET API_KEY = NULL, UPDATED_DATE = :UPDATED_DATE WHERE BROKER_CREDENTIALS_ID = :BROKER_CREDENTIALS_ID", nativeQuery = true)
	public void deleteApiKey(@Param(value = "UPDATED_DATE") LocalDateTime updatedDate, @Param(value = "BROKER_CREDENTIALS_ID") int brokerCredentialsId);
	
	@Modifying
	@Query(value = "INSERT INTO BROKER_CREDENTIALS(USERNAME,PASSWORD,FIRST_NAME,LAST_NAME,CREATED_DATE) VALUES(:USERNAME,:PASSWORD,:FIRST_NAME,:LAST_NAME,:CREATED_DATE)", nativeQuery = true)
	public void createAccount(@Param(value = "USERNAME") String username, @Param(value = "PASSWORD") String password, @Param(value = "FIRST_NAME") String firstName, @Param(value = "LAST_NAME") String lastName, @Param(value = "CREATED_DATE") LocalDateTime createdDate);

	@Modifying
	@Query(value = "UPDATE BROKER_CREDENTIALS SET FIRST_NAME = :FIRST_NAME, LAST_NAME = :LAST_NAME WHERE BROKER_CREDENTIALS_ID = :BROKER_CREDENTIALS_ID", nativeQuery = true)
	public void changeName(@Param(value = "FIRST_NAME") String firstName, @Param(value = "LAST_NAME") String lastName, @Param(value = "BROKER_CREDENTIALS_ID") int brokerCredentialsId);
	
	@Modifying
	@Query(value = "UPDATE BROKER_CREDENTIALS SET PASSWORD = :PASSWORD WHERE BROKER_CREDENTIALS_ID = :BROKER_CREDENTIALS_ID", nativeQuery = true)
	public void changePassword(@Param(value = "PASSWORD") String password, @Param(value = "BROKER_CREDENTIALS_ID") int brokerCredentialsId);
}
