package org.bejb4.finalproject.controller;

import org.bejb4.finalproject.model.Flight;
import org.bejb4.finalproject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
        Flight flight1 = flightService.addFlight(flight);
        return new ResponseEntity<>(flight1, HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<Flight>> getAllFlight(){
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
    public ResponseEntity<String> updateFlight(@PathVariable Long idFlight, @RequestBody Flight flight){
        flightService.updateFlight(idFlight, flight);
        return new ResponseEntity<String>("Update Data Flight Done", HttpStatus.OK);
    }
    @DeleteMapping("/{idFlight}")
    public ResponseEntity<String> deleteFlightById(@PathVariable Long idFlight){
        flightService.deleteFlightById(idFlight);
        return ResponseEntity.ok("Delete Data Flight Done");
    }
}
