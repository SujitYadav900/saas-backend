package com.whatsappbuisness.wsbuissness.newagentsupport;

import org.springframework.data.repository.CrudRepository;

public interface AgentIndexRepo extends CrudRepository<AgentIndex, Long> {

	AgentIndex findByAdminAccountId(long accountId);

}
