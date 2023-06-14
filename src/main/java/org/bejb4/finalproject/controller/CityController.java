package org.bejb4.finalproject.controller;

import org.bejb4.finalproject.model.City;
import org.bejb4.finalproject.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;
    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody City city){
        City city1 = cityService.addCity(city);
        return new ResponseEntity<>(city1, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<City>> getAllCity(){
        List<City> AllCity = cityService.getAllCity();
        return ResponseEntity.ok(AllCity);
    }
    @GetMapping("/{idCity}")
    public ResponseEntity<City> getCityById(@PathVariable Long idCity){
        Optional<City> city = cityService.getCityById(idCity);
        if (city.isPresent()){
            return new ResponseEntity<>(city.get(), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{idCity}")
    public ResponseEntity<String> deleteCityById(@PathVariable Long idCity){
        cityService.deleteCityById(idCity);
        return ResponseEntity.ok("Delete City By Id Success");
    }
}
