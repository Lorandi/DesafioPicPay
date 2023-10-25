package com.DesafioPicPay.resource;

import com.DesafioPicPay.dto.TransferDTO;
import com.DesafioPicPay.dto.TransferSummaryDTO;
import com.DesafioPicPay.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferResource {
    private final TransferService service;

    @PostMapping
    public ResponseEntity<TransferSummaryDTO> createTransfer(@RequestBody TransferDTO transferDTO) {
        return new ResponseEntity<>(service.createTransfer(transferDTO), CREATED);
    }

    @GetMapping
    public List<TransferSummaryDTO> findAll() {
        return ResponseEntity.ok(service.findAll()).getBody();
    }

    @GetMapping("/{id}")
    public TransferSummaryDTO findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id)).getBody();
    }
}



