package com.whatsappbuisness.wsbuissness.CombinePackadge.ClientSignUp;

import com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao.TextSMSSendingDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.ResponseServiceDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackage.SubScriptionPackageDao;

import java.util.List;

public interface ClientSignUpService {
    ClientSignUpDao insert(ClientSignUpDao clientSignUpDao);

    ResponseServiceDao sendTextSms(TextSMSSendingDao textSMSSendingDao);

    List<ClientSignUpDao> getClient(ClientSignUpDao clientSignUpDao);
    List<ClientSignUpDao> getClientByOnlyClientNumber(ClientSignUpDao clientSignUpDao);
    List<ClientSignUpDao> getClientByOnlyWhatsappNumber(String number);


    ClientSignUpDao addSubscriptionPackageToClient(ClientSignUpDao clientSignUpDao, SubScriptionPackageDao subScriptionPackageDao);

    ClientSignUpDao getById(String accountId);

    ClientSignUpDao update(ClientSignUpDao clientSignUpDao);

    List<ClientSignUpDao> get(FilterDao filterDao);
}


