package org.bejb4.finalproject.service;

import lombok.extern.slf4j.Slf4j;
import org.bejb4.finalproject.model.City;
import org.bejb4.finalproject.repository.CityRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CityService {
    @Autowired
    private CityRepostory cityRepostory;
    public City addCity(City city){
        log.info("Add City Success");
        return cityRepostory.save(city);
    }
    public List<City> getAllCity(){
        log.info("Get All City Success");
        return cityRepostory.findAll();
    }
    public Optional<City> getCityById(Long idCity){
        log.info("Get City By id Success");
        return cityRepostory.findById(idCity);
    }
    public void deleteCityById(Long idCity){
        log.info("Delete City By Id Success");
        cityRepostory.deleteById(idCity);
    }
}
