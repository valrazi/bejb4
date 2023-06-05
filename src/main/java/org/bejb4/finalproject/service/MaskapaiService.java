package org.bejb4.finalproject.service;

import org.bejb4.finalproject.model.Maskapai;
import org.bejb4.finalproject.repository.MaskapaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaskapaiService {
    @Autowired
    private MaskapaiRepository maskapaiRepository;

    public Maskapai createMaskapai(Maskapai maskapai) {
        return maskapaiRepository.save(maskapai);
    }

    public List<Maskapai> getAllMaskapai() {
        return maskapaiRepository.findAll();
    }

    public Optional<Maskapai> getById(Long idMaskapai) {
        return maskapaiRepository.findById(idMaskapai);
    }

    public void deleteMaskapai(Long idMaskapai) {
        maskapaiRepository.deleteById(idMaskapai);
    }

}