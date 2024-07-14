package com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao;
/*      
 Author=Supreet Singh
 Date= 08/02/21 4:08 PM
*/

public enum  CreditType {
    PREPAID(1), POSTPAID(2);
    public final int value;
    CreditType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
