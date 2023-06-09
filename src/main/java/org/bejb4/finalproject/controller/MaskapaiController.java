package org.bejb4.finalproject.controller;

import org.bejb4.finalproject.model.Maskapai;
import org.bejb4.finalproject.repository.MaskapaiRepository;
import org.bejb4.finalproject.service.MaskapaiService;
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
@RequestMapping("/maskapai")
public class MaskapaiController {
    @Autowired
    private MaskapaiRepository maskapaiRepository;

    @Autowired
    private MaskapaiService maskapaiService;

    @Autowired
    private SortAscDesc sortAscDesc;

    // Insert Data To maskapai
    @PostMapping("/create")
    public ResponseEntity<MessageModel> insertData(@RequestBody List<Maskapai> param) {
        MessageModel msg = new MessageModel();
        try {
            List<Maskapai> maskapaiList = new ArrayList<>();
            for (Maskapai data : param) {
                Maskapai maskapai = new Maskapai();

                maskapai.setNamaMaskapai(data.getNamaMaskapai());
                maskapai.setLogoMaskapai(data.getLogoMaskapai());

                maskapaiList.add(maskapai);
            }
            maskapaiRepository.saveAll(maskapaiList);

            msg.setStatus(true);
            msg.setMessage("Success to inserted data..");
            msg.setData(maskapaiList);

            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    // Update Data From maskapai
    @PatchMapping("/updateOptional")
    public ResponseEntity<MessageModel> updateData(@RequestBody List<Maskapai> param) {
        MessageModel msg = new MessageModel();
        try {
            List<Maskapai> maskapaiList = new ArrayList<>();
            for (Maskapai data : param) {
                Optional<Maskapai> maskapaiOpt = maskapaiService.getById(data.getIdMaskapai());

                if (maskapaiOpt.isPresent()) {
                    Maskapai maskapai = maskapaiOpt.get();

                    maskapai.setNamaMaskapai(data.getNamaMaskapai());
                    maskapai.setLogoMaskapai(data.getLogoMaskapai());

                    maskapaiList.add(maskapai);
                } else {
                    throw new RuntimeException("Maskapai not found for id : " + data.getIdMaskapai());
                }
            }
            maskapaiRepository.saveAll(maskapaiList);

            msg.setStatus(true);
            msg.setMessage("Success to updated data..");
            msg.setData(maskapaiList);

            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    // Delete (byId) From maskapai
    @DeleteMapping("/delete/{idMaskapai}")
    public ResponseEntity<MessageModel> deleteById(@PathVariable("idMaskapai") Long idMaskapai) {
        MessageModel msg = new MessageModel();
        try {
            maskapaiService.deleteMaskapai(idMaskapai);

            msg.setStatus(true);
            msg.setMessage("Success to deleted data..");
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    // Get (byId) From maskapai
    @GetMapping("/getData/{idMaskapai}")
    public ResponseEntity<MessageModel> getById(@PathVariable("idMaskapai") Long idMaskapai) {
        MessageModel msg = new MessageModel();
        try {
            Optional<Maskapai> data = maskapaiService.getById(idMaskapai);

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

    // Get All (List) From maskapai
    @GetMapping("/getList")
    public ResponseEntity<MessageModel> getListData() {
        MessageModel msg = new MessageModel();
        try {
            List<Maskapai> data = (List<Maskapai>) maskapaiService.getAllMaskapai();

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

    // Get All (Pagination) From maskapai
    @GetMapping("/pagination")
    public ResponseEntity<MessageModelPagination> getDataPagination(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                                                    @RequestParam(value = "size",defaultValue = "10") Integer size,
                                                                    @RequestParam(value = "sort", required=false) String sort,
                                                                    @RequestParam(value = "urutan", required=false) String urutan) {
        MessageModelPagination msg = new MessageModelPagination();
        try {
            Sort objSort = sortAscDesc.getSortingData(sort,urutan);
            Pageable pageRequest = objSort == null ? PageRequest.of(page, size) : PageRequest.of(page, size,objSort);

            Page<Maskapai> data = maskapaiRepository.findAll(pageRequest);

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