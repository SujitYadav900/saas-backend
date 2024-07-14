package com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao;

import java.util.HashMap;

public class WhatsappCreditDao {

    private String expiryDate;
    private int expiryDateFilter;
    private double perSpendDeduction;
    private CreditType accountType;
    private DeductionType deductionType;
    private double postPaidMaximumAllowed;
    private HashMap<String,String> extraParameters;

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getExpiryDateFilter() {
        return expiryDateFilter;
    }

    public void setExpiryDateFilter(int expiryDateFilter) {
        this.expiryDateFilter = expiryDateFilter;
    }

    public double getPerSpendDeduction() {
        return perSpendDeduction;
    }

    public void setPerSpendDeduction(double perSpendDeduction) {
        this.perSpendDeduction = perSpendDeduction;
    }

    public CreditType getAccountType() {
        return accountType;
    }

    public void setAccountType(CreditType accountType) {
        this.accountType = accountType;
    }

    public DeductionType getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(DeductionType deductionType) {
        this.deductionType = deductionType;
    }

    public double getPostPaidMaximumAllowed() {
        return postPaidMaximumAllowed;
    }

    public void setPostPaidMaximumAllowed(double postPaidMaximumAllowed) {
        this.postPaidMaximumAllowed = postPaidMaximumAllowed;
    }

    public HashMap<String, String> getExtraParameters() {
        return extraParameters;
    }

    public void setExtraParameters(HashMap<String, String> extraParameters) {
        this.extraParameters = extraParameters;
    }


    public WhatsappCreditDao(String expiryDate, int expiryDateFilter, double perSpendDeduction, CreditType accountType, DeductionType deductionType, double postPaidMaximumAllowed) {
        this.expiryDate = expiryDate;
        this.expiryDateFilter = expiryDateFilter;
        this.perSpendDeduction = perSpendDeduction;
        this.accountType = accountType;
        this.deductionType = deductionType;
        this.postPaidMaximumAllowed = postPaidMaximumAllowed;
    }

    @Override
    public String toString() {
        return "WhatsappCreditDao{" +
                "expiryDate='" + expiryDate + '\'' +
                ", expiryDateFilter=" + expiryDateFilter +
                ", perSpendDeduction=" + perSpendDeduction +
                ", accountType=" + accountType +
                ", deductionType=" + deductionType +
                ", postPaidMaximumAllowed=" + postPaidMaximumAllowed +
                ", extraParameters=" + extraParameters +
                '}';
    }
}
