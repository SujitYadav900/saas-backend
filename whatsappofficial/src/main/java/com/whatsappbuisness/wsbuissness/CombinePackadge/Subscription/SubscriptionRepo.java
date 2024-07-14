package com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription;
/*
 Author=Supreet Singh
 Date= 09/03/21 11:09 AM
 */

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepo extends CrudRepository<SubscriptionDao,String> {
	@Query("from SubscriptionDao  where accountId=?1 and isActive=TRUE")
	SubscriptionDao findByAccountAndActive(long accountId);

	@Query("from SubscriptionDao  where isActive=TRUE ")
	List<SubscriptionDao> findActive();

	@Query("from SubscriptionDao  where expiryDateFilter<?1 and  isActive=TRUE ")
	List<SubscriptionDao> get(int date);

	@Query(value = "select * from WSBUISSNES.Tbl_Subscription where accountId = ?1 order by updateAtFilter desc limit 0,1", nativeQuery = true)
	SubscriptionDao findByAccountId(long accountId);

	List<SubscriptionDao> findByDlrReportOnEmailTrue();
	List<SubscriptionDao> findByEnableGoogleSheetTrue();

	List<SubscriptionDao>  findByAgentAssignManuallyTrue();



}
