package com.whatsappbuisness.wsbuissness.CombinePackadge.Country;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ChatSide;

import java.util.List;

public interface CountryService {
    /**
     *
     * @param phn phonenumber with country code donot add any symbot for eg
     *      919999420594
     * @param chatSide if chatside is User then it will throw error if country
     *                code is not mapped in the system
     * @return check enum for return status
     */
    CountryWiseRetDao findByCode(String phn, ChatSide chatSide);

    /**
     * Will refresh cache for country
     */
    void refreshCashe();
    Iterable<CountryDao> getAll();
    CountryDao save(CountryDao countryDao);


}
