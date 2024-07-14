package com.example.maxcrm.MaxCrm.Utility;


import com.example.maxcrm.MaxCrm.CombinePackage.MyOperator.RecordingUrlDao;
import com.example.maxcrm.MaxCrm.CombinePackage.MyOperator.RecordingUrlServiceimpl;
import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

public class Test {


    public static void main(String[] args) throws  Exception {
        Logger logger = LoggerFactory.getLogger(Test.class);

        Map<String,String> map = new HashMap();
        map.put("1","one");
        map.put("2","two");
        map.put("3","three");
        ArrayList<Map> list = new ArrayList<>();
        list.add(map);
        list.add(map);

        JsonObject js = new JsonObject();
        System.out.println(js.getAsJsonObject(list.toString()));

    }


}
