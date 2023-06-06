package org.bejb4.finalproject.controller;

import org.bejb4.finalproject.model.Jadwal;
import org.bejb4.finalproject.repository.JadwalRepository;
import org.bejb4.finalproject.service.JadwalService;
import org.bejb4.finalproject.service.SortAscDesc;
import org.bejb4.finalproject.utils.MessageModel;
import org.bejb4.finalproject.utils.MessageModelPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jadwal")
public class JadwalController {
    @Autowired
    private JadwalRepository jadwalRepository;

    @Autowired
    private JadwalService jadwalService;

    @Autowired
    private SortAscDesc sortAscDesc;

    // Insert Data To jadwal
    @PostMapping("/create")
    public ResponseEntity<MessageModel> insertData(@RequestBody List<Jadwal> param) {
        MessageModel msg = new MessageModel();
        try {
            List<Jadwal> jadwalList = new ArrayList<>();
            for (Jadwal data : param) {
                Jadwal jadwal = new Jadwal();

                jadwal.setIdJadwal(data.getIdJadwal());
                jadwal.setNoPenerbangan(data.getNoPenerbangan());
                jadwal.setTglKeberangkatan(data.getTglKeberangkatan());
                jadwal.setJamKeberangkatan(data.getJamKeberangkatan());
                jadwal.setJamKedatangan(data.getJamKedatangan());
                jadwal.setKotaKeberangkatan(data.getKotaKeberangkatan());
                jadwal.setKotaKedatangan(data.getKotaKedatangan());

                jadwalList.add(jadwal);
            }
            jadwalRepository.saveAll(jadwalList);

            msg.setStatus(true);
            msg.setMessage("Success to inserted data..");
            msg.setData(jadwalList);

            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    // Delete (byId) From jadwal
    @DeleteMapping("/delete/{idJadwal}")
    public ResponseEntity<MessageModel> deleteById(@PathVariable("idJadwal") Long idJadwal) {
        MessageModel msg = new MessageModel();
        try {
            jadwalService.deleteJadwal(idJadwal);

            msg.setStatus(true);
            msg.setMessage("Success to deleted data..");
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    // Get (byId) From jadwal
    @GetMapping("/getData/{idJadwal}")
    public ResponseEntity<MessageModel> getById(@PathVariable("idJadwal") Long idJadwal) {
        MessageModel msg = new MessageModel();
        try {
            Optional<Jadwal> data = jadwalService.getById(idJadwal);

            msg.setStatus(true);
            msg.setMessage("Success to get data..");
            msg.setData(data);

            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    // Get All (List) From jadwal
    @GetMapping("/getList")
    public ResponseEntity<MessageModel> getListData() {
        MessageModel msg = new MessageModel();
        try {
            List<Jadwal> data = (List<Jadwal>) jadwalService.getAllJadwal();

            msg.setStatus(true);
            msg.setMessage("Success to get all data..");
            msg.setData(data);

            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    // Get All (Pagination) From jadwal
    @GetMapping("/pagination")
    public ResponseEntity<MessageModelPagination> getDataPagination(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                                                    @RequestParam(value = "size",defaultValue = "10") Integer size,
                                                                    @RequestParam(value = "sort", required=false) String sort,
                                                                    @RequestParam(value = "urutan", required=false) String urutan) {
        MessageModelPagination msg = new MessageModelPagination();
        try {
            Sort objSort = sortAscDesc.getSortingData(sort,urutan);
            Pageable pageRequest = objSort == null ? PageRequest.of(page, size) : PageRequest.of(page, size,objSort);

            Page<Jadwal> data = jadwalRepository.findAll(pageRequest);

            msg.setStatus(true);
            msg.setMessage("Success to get all data..");
            msg.setData(data.getContent());
            msg.setCurrentPage(data.getNumber());
            msg.setTotalPages(data.getTotalPages());
            msg.setTotalItems((int) data.getTotalElements());
            msg.setNumberOfElement(data.getNumberOfElements());

            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

}