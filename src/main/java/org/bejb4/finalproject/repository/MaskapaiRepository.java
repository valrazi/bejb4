package org.bejb4.finalproject.repository;

import org.bejb4.finalproject.model.Maskapai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaskapaiRepository extends JpaRepository<Maskapai, Long> {
}