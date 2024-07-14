package com.whatsappbuisness.wsbuissness.CombinePackadge.Country;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.StatusDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ChatSide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryRepo countryRepo;
    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);

    private static final HashMap<String, CountryDao> hashMap = new HashMap<>();

    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    @Override
    public CountryWiseRetDao findByCode(String phn, ChatSide chatSide) {
        CountryWiseRetDao countryWiseRetDao;
        try {
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse("+"+phn, "");
            String countryCode = String.valueOf(numberProto.getCountryCode());
            logger.info("Found country code  {} for number {}",countryCode,phn);
            if(hashMap.containsKey(countryCode))
            {
                CountryDao countryDao = hashMap.get(countryCode);
                countryWiseRetDao = new CountryWiseRetDao(StatusDao.OK, countryDao);
            }else{
                throw new Exception("Not Found!!");
            }


        } catch (Exception ew) {
            if (chatSide == ChatSide.User) {
                countryWiseRetDao = new CountryWiseRetDao(StatusDao.NOTFOUND, null);
            } else {
                CountryDao countryDao = hashMap.get("-1");
                countryWiseRetDao = new CountryWiseRetDao(StatusDao.OK, countryDao);
            }

        }
        return countryWiseRetDao;
    }


    @Bean
    @Override
    public void refreshCashe() {
        logger.info("Refreshing cache country");
        Iterable<CountryDao> al = countryRepo.findAll();
        for (CountryDao countryDao : al) {
            hashMap.put(countryDao.getCode(), countryDao);

        }
    }

    @Override
    public Iterable<CountryDao> getAll() {
        return countryRepo.findAll();
    }

    @Override
    public CountryDao save(CountryDao countryDao) {
        return countryRepo.save(countryDao);
    }
}
