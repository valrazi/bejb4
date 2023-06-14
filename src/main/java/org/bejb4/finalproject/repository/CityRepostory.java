package org.bejb4.finalproject.repository;

import org.bejb4.finalproject.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepostory extends JpaRepository<City, Long> {
//    Optional<City> findCityById(Long idCity);
}
