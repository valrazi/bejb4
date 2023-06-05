package org.bejb4.finalproject.repository;

import org.bejb4.finalproject.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("Select f From finalproject WHERE f.idFlight =?1")
    Optional<Flight> findByIdFlight(Long idFlight);
    @Query("Select f From finalproject WHERE f.maskapai =?1")
    List<Flight> findByMaskapai(String Maskapai);
}
