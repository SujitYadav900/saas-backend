package com.whatsappbuisness.wsbuissness.CombinePackadge.Features.DirectChatFeature.UserAsignDao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserAssignRepo extends CrudRepository<UserAssignDao,UserCompositeKeyDao> {
List<UserAssignDao> getAllByAccountId(long accountId);

}


