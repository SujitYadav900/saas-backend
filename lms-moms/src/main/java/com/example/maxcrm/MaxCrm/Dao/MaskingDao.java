package com.example.maxcrm.MaxCrm.Dao;

import java.util.HashMap;

public class MaskingDao {
    public boolean isNumberMasking() {
        return numberMasking;
    }

    public void setNumberMasking(boolean numberMasking) {
        this.numberMasking = numberMasking;
    }

    public boolean isEmailMasking() {
        return emailMasking;
    }

    @Override
    public String toString() {
        return "MaskingDao{" +
                "numberMasking=" + numberMasking +
                ", emailMasking=" + emailMasking +
                '}';
    }

    public void setEmailMasking(boolean emailMasking) {
        this.emailMasking = emailMasking;
    }

    private boolean numberMasking;
    private boolean emailMasking;
    public MaskingDao convertHashMapToMasking(HashMap<String,String> hashMap)
    {
        if(hashMap.containsKey("Email_Masking"))
        {
            emailMasking=true;
        }
         if(hashMap.containsKey("Phonenumber_Masking"))
        {
            numberMasking=true;
        }
        System.out.println("this is string"+ this);
        return this;
    }
}
