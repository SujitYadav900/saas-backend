package com.whatsappbuisness.wsbuissness.CombinePackadge.Features.DirectChatFeature.UserAsignDao;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;

import java.util.List;

public interface UserAssignService {
    List<UserAssignDao> getByAccountId(long accountId);

    UserAssignDao save(UserAssignDao userAssignDao);


}
