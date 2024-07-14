package com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserOrBuisnessRepo extends CrudRepository<UserOrBuisnessIntiatedDao,Long> {

    UserOrBuisnessIntiatedDao findTopByDstAndCountryCodeAndAccountIdOrderByIdDesc(String dst,String counytryCode,long accountId);
    UserOrBuisnessIntiatedDao findTopByDstAndCountryCodeAndAccountIdAndCategoryOrderByIdDesc(String dst,String counytryCode,long accountId, String category);
    List<UserOrBuisnessIntiatedDao> findAllByAccountIdAndDateFilterGreaterThanEqualAndDateFilterLessThanEqual(long accountId,int startdate,int enddate);

    @Override
    void deleteById(Long id);
}
