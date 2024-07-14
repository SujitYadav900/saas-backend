package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.CityDao;
import com.example.maxcrm.MaxCrm.Repo.CityRepository;
import com.example.maxcrm.MaxCrm.Service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);
    @Autowired
    CityRepository cityRepository;
    @Override
    public Iterable<CityDao> findAll() {

        return cityRepository.findAll();
    }

    @Override
    public Iterable<CityDao> findByState(String state) {
        logger.info("Finding By State {}",state);
        return cityRepository.findCityByState(state);
    }

    @Override
    public CityDao insert(CityDao cityDao) {
        logger.info("Inserting {}",cityDao);
        return cityRepository.save(cityDao);
    }

    @Override
    public CityDao update(CityDao cityDao) {
        logger.info("Updating {}",cityDao);
        return cityRepository.save(cityDao);
    }

    @Override
    public void delete(String city) {
        logger.info("Deleting {}",city);
    cityRepository.deleteById(city);
    }
}
