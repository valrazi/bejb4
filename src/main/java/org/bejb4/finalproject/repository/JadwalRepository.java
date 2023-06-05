package org.bejb4.finalproject.repository;

import org.bejb4.finalproject.model.Jadwal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JadwalRepository extends JpaRepository<Jadwal, Long> {
}