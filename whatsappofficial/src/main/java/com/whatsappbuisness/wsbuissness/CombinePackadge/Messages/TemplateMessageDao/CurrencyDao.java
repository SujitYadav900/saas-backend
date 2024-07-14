package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao;
/*
 Author=Supreet Singh
 Date= 09/03/21 10:34 AM
*/

import java.io.Serializable;

public class CurrencyDao  implements Serializable {

    @Override
    public String toString() {
        return "CurrencyDao{" +
                "fallback_value='" + fallback_value + '\'' +
                ", code='" + code + '\'' +
                ", amount_1000=" + amount_1000 +
                '}';
    }

    public String getFallback_value() {
        return fallback_value;
    }

    public void setFallback_value(String fallback_value) {
        this.fallback_value = fallback_value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getAmount_1000() {
        return amount_1000;
    }

    public void setAmount_1000(double amount_1000) {
        this.amount_1000 = amount_1000;
    }

    private String fallback_value;
    private String code;
    private double amount_1000;
}
