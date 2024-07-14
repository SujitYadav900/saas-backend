package com.whatsappbuisness.wsbuissness.CombinePackadge.CloudApiErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
public class CloudApiErrorCodeServiceImpl implements CloudApiErrorCodeService{

    private static HashMap<String, CloudApiErrorCodeDao> hashMap=new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(CloudApiErrorCodeServiceImpl.class);

    @Autowired
     CloudApiErrorCodeRepo cloudApiErrorCodeRepo;

    @Override
    public CloudApiErrorCodeDao getCloudApiErrorStatus(String errorCode) {
        return hashMap.get(errorCode);
    }

    @Override
    public String refreshCache() {
        List<CloudApiErrorCodeDao> cloudApiErrorCodeDaoList = cloudApiErrorCodeRepo.findAll();
        for(CloudApiErrorCodeDao cloudApiErrorCodeDao : cloudApiErrorCodeDaoList){
            hashMap.put(cloudApiErrorCodeDao.getCode(), cloudApiErrorCodeDao );
            logger.info(""+hashMap.get(cloudApiErrorCodeDao.getCode()));
        }
        return "200";
    }

}
