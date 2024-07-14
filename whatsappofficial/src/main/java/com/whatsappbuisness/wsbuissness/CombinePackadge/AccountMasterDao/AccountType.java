package com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao;
/*      
 Author=Supreet Singh
 Date= 11/02/21 2:21 PM
*/

public enum AccountType {

    PRODUCTION(1), TESTING(2);
    public final int value;
    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
