package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao;
/*
 Author=Supreet Singh
 Date= 04/02/21 5:29 PM
*/

import java.io.Serializable;

public class LanguageDao implements Serializable {
    @Override
    public String toString() {
        return "LanguageDao{" +
                "policy='" + policy + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String policy;
private String code;

}
