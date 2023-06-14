package org.bejb4.finalproject.service;

import lombok.extern.slf4j.Slf4j;
import org.bejb4.finalproject.model.Flight;
import org.bejb4.finalproject.model.Jadwal;
import org.bejb4.finalproject.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
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
    public List<Flight> getAllFlight(){//dapatkan semua
        log.info("Get All Data Flight Success");
        return flightRepository.findAll();
    }
    public Optional<Flight> getFlightById(Long idFlight){
        log.info("Get Flight By Id Success");
        return flightRepository.findByIdFlight(idFlight);
    }
    public Flight updateFlight(Long id, Flight flight){

        //VALIDASI

        Flight flight1 = flightRepository.findByIdFlight(id).get();

        if(flight.getHargaEkonomi() != 0){
            flight1.setHargaEkonomi(flight.getHargaEkonomi());
        }
        if (flight.getHargaBisnis() != 0){
            flight1.setHargaBisnis(flight.getHargaBisnis());
        }
        if (flight.getJmlKursiSeluruh() != 0){
            flight1.setJmlKursiSeluruh(flight.getJmlKursiSeluruh());
        }
        if(flight.getJmlKursiBisnis() !=0){
            flight1.setJmlKursiBisnis(flight.getJmlKursiBisnis());
        }
        if (flight.getJmlKursiEkonomi() != 0){
            flight1.setJmlKursiEkonomi(flight.getJmlKursiEkonomi());
        }
        log.info("Update Data Flight By Id Success");
        return flightRepository.save(flight1);
    }
    public void deleteFlightById(Long id){
        log.info("Delete Data Flight By Id Success");
        flightRepository.deleteById(id);
    }
}
