package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.FacebookDao;
import com.example.maxcrm.MaxCrm.Dao.FacebookPayloadInnerDao;

import java.util.List;

public interface FacebookAddService {

    List<FacebookDao> getLeadDetailsById(List<FacebookPayloadInnerDao> al);
}
