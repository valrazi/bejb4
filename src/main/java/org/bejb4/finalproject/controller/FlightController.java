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
    @PostMapping("/addFlight")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
        Flight flight1 = flightService.addFlight(flight);
        return new ResponseEntity<>(flight1, HttpStatus.CREATED);
    }
    @GetMapping("getAllFlight")
    public ResponseEntity<List<Flight>> getAllFlight(){
        List<Flight> allFlight = flightService.getAllFlight();
        return ResponseEntity.ok(allFlight);
    }
    @GetMapping("getFlightById/{flightId}")
    public ResponseEntity<Flight> getFlightById(@RequestParam("idFlight") Long id){
        Optional<Flight> flight = flightService.getFlightById(id);
        if(flight.isPresent()){
            return new ResponseEntity<>(flight.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/updateFlight")
    public ResponseEntity<String> updateFlight(@RequestParam(name = "idFlight") Long id, @RequestBody Flight flight){
        flightService.updateFlight(id, flight);
        return new ResponseEntity<String>("Update Data Flight Done", HttpStatus.OK);
    }
    public ResponseEntity<String> deleteFlightById(@RequestParam(name = "idFlight") Long id){
        flightService.deleteFlightById(id);
        return ResponseEntity.ok("Delete Data Flight Done");
    }
}
