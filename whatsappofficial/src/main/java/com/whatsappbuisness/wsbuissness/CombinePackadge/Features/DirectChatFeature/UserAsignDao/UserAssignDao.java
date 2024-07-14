package com.whatsappbuisness.wsbuissness.CombinePackadge.Features.DirectChatFeature.UserAsignDao;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionFeature.SubscriptionFeaturePermission.SubcriptionFeaturePermissionComponsite;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "Tbl_User_Assign_DC_Feature")
@IdClass(UserCompositeKeyDao.class)
public class UserAssignDao implements Serializable {
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Id
    private long accountId;
    @Id
    private long userId;

}
