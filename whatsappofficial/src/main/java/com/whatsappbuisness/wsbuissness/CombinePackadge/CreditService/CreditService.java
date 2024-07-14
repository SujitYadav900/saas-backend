package com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService;
/*
 Author=Supreet Singh
 Date= 09/03/21 1:01 PM
*/


import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.TransationType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.VoucherType;

import java.io.IOException;

public interface CreditService {
    /**
     *
     * @param transationType
     * @param amount
     * @param refId
     * @param voucherType
     * @param accountId
     */
    void creditServer(TransationType transationType, double amount, String refId, VoucherType voucherType, long accountId);

    /**
     *
     * @param accointId
     * @param amount amount to be deducted
     * @return if status==200 then everything is okay
     */
    ResponseServiceDao validateCredit(long accointId,double amount);
}
