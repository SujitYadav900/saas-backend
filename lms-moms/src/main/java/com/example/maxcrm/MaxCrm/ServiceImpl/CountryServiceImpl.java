package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.CountryDao;
import com.example.maxcrm.MaxCrm.Repo.CountryRepository;
import com.example.maxcrm.MaxCrm.Service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
    Logger logger = LoggerFactory.getLogger(CountryService.class);
    @Autowired
    CountryRepository countryRepository;
    @Override
    public CountryDao insert(CountryDao dao) {
        logger.info("Inserting {}",dao);
        return countryRepository.save(dao);
    }

    @Override
    public CountryDao update(CountryDao dao) {
        logger.info("Updating {}",dao);
        return countryRepository.save(dao);
    }

    @Override
    public void delete(String country) {
        logger.info("Deleting {}",country);
         countryRepository.deleteById(country);
    }

    @Override
    public Iterable<CountryDao> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public Iterable<CountryDao> getAllActive() {
        return countryRepository.findActive();
    }
}
