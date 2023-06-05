package org.bejb4.finalproject.service;

import org.bejb4.finalproject.model.Jadwal;
import org.bejb4.finalproject.repository.JadwalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JadwalService {
    @Autowired
    private JadwalRepository jadwalRepository;

    public Jadwal createJadwal(Jadwal jadwal) {
        return jadwalRepository.save(jadwal);
    }

    public List<Jadwal> getAllJadwal() {
        return jadwalRepository.findAll();
    }

    public Optional<Jadwal> getById(Long idJadwal) {
        return jadwalRepository.findById(idJadwal);
    }

    public void deleteJadwal(Long idJadwal) {
        jadwalRepository.deleteById(idJadwal);
    }

}