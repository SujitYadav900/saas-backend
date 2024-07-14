package com.example.maxcrm.MaxCrm.Dao.HIS;

import com.example.maxcrm.MaxCrm.Dao.LeadMasterDao;
import com.example.maxcrm.MaxCrm.Dao.LeadStageDocument;
import com.example.maxcrm.MaxCrm.Dao.StageOptionDocument;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HisAppointment {
    private int code;

    @Override
    public String toString() {
        return "HisConfirmedAppointment{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiInnerData getData() {
        return data;
    }

    public void setData(ApiInnerData data) {
        this.data = data;
    }

    private String message;
    private ApiInnerData data;
    public HisAppointment convertJsonToObject(String json)
    {
       return new Gson().fromJson(json,HisAppointment.class);
    }




}
