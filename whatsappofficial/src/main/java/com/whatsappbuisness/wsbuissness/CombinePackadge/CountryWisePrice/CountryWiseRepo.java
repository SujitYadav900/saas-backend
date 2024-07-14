package com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryWiseRepo extends CrudRepository<CountryWisePriceDao,CountryWisePricePk> {

    List<CountryWisePriceDao> findAllByAccountIdOrderByCountryCodeAsc(long id);

	List<CountryWisePriceDao> findByAccountId(long parentId);
}
