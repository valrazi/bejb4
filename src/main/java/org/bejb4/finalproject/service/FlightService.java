package org.bejb4.finalproject.service;

import lombok.extern.slf4j.Slf4j;
import org.bejb4.finalproject.model.Flight;
import org.bejb4.finalproject.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public Flight addFlight(Flight flight){
        log.info("Add data Flight Success");
        return flightRepository.save(flight);
    }
    public List<Flight> getAllFlight(){
        log.info("Get All Data Flight Success");
        return flightRepository.findAll();
    }
    public Optional<Flight> getFlightById(Long idFlight){
        log.info("Get Flight By Id Success");
        return flightRepository.findByIdFlight(idFlight);
    }
    public Flight updateFlight(Long id, Flight flight){
        Flight flight1 = flightRepository.findByIdFlight(id).get();
        flight1.setHargaEkonomi(flight.getHargaEkonomi());
        flight1.setHargaBisnis(flight.getHargaBisnis());
        flight1.setJmlKursiEkonomi(flight.getJmlKursiEkonomi());
        flight1.setJmlKursiBisnis(flight.getJmlKursiBisnis());
        log.info("Update Data Flight By Id Success");
        return flightRepository.save(flight1);
    }
    public void deleteFlightById(Long id){
        log.info("Delete Data Flight By Id Success");
        flightRepository.deleteById(id);
    }
}
