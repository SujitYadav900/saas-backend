package com.example.maxcrm.MaxCrm.Dao.HIS;

import com.google.gson.Gson;

public class TokenGeneration {


    public TokenInner getData() {
        return data;
    }

    public void setData(TokenInner data) {
        this.data = data;
    }
   public TokenGeneration convertStringJsonToObject(String json)
    {
       return new Gson().fromJson(json,TokenGeneration.class);
    }

    private TokenInner data;
}
