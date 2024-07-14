package com.whatsappbuisness.wsbuissness.newagentsupport;





import java.util.List;

import org.springframework.data.repository.CrudRepository;
public interface AgentCustNumberMappingRepo extends CrudRepository<AgentCustNumberMapping, Long>{

	AgentCustNumberMapping findBycustMobileNumber(String valueOf);

	boolean existsByCustMobileNumber(String mobileNumber);
	AgentCustNumberMapping findByAdminAccountIdAndCustMobileNumber(long agentAccountId, String mobileNumber);

}
