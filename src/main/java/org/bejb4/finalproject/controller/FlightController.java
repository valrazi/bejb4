package org.bejb4.finalproject.controller;

import org.bejb4.finalproject.model.Flight;
import org.bejb4.finalproject.repository.FlightRepository;
import org.bejb4.finalproject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightService flightService;
    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
        Flight flight1 = flightService.addFlight(flight);
        return new ResponseEntity<>(flight1, HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<Flight>> getAllFlight(){//dapatkan semua
        List<Flight> allFlight = flightService.getAllFlight();
        return ResponseEntity.ok(allFlight);
    }
    @GetMapping("/{idFlight}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long idFlight){
        Optional<Flight> flight = flightService.getFlightById(idFlight);

        if(flight.isPresent()){
            return new ResponseEntity<>(flight.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{idFlight}")
//    public ResponseEntity<String> updateFlight(@PathVariable Long idFlight, @RequestBody Flight flight){
    public ResponseEntity<String> updateFlight(@RequestBody List<Flight> param){
        try{
            List<Flight> flightList = new ArrayList<>();
            for(Flight data : param){
                Optional<Flight> flightOptional = flightService.getFlightById(data.getIdFlight());
                if(flightOptional.isPresent()){
                    Flight flight = flightOptional.get();
                    flight.setJmlKursiSeluruh(data.getJmlKursiSeluruh());
                    flight.setJmlKursiEkonomi(data.getJmlKursiEkonomi());
                    flight.setJmlKursiBisnis(data.getJmlKursiBisnis());
                    flight.setHargaEkonomi(data.getHargaEkonomi());
                    flight.setHargaBisnis(data.getHargaBisnis());

                    flightList.add(flight);
                }
                else{
                    throw new RuntimeException("Flight not found by id : " + data.getIdFlight());
                }
            }
            flightRepository.saveAll(flightList);
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.OK);
        }
        catch (Exception e){
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
//        flightService.updateFlight(idFlight, flight);
//        return new ResponseEntity<String>("Update Data Flight Done", HttpStatus.OK);
    }
    @DeleteMapping("/{idFlight}")
    public ResponseEntity<String> deleteFlightById(@PathVariable Long idFlight){
        flightService.deleteFlightById(idFlight);
        return ResponseEntity.ok("Delete Data Flight Done");
    }
}
