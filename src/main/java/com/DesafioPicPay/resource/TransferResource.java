package com.DesafioPicPay.resource;

import com.DesafioPicPay.entity.Transfer;
import com.DesafioPicPay.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferResource {
    private final TransferService service;

    @GetMapping("/{id}")

    public Transfer findById(@PathVariable Long id) {
        return service.findById(id);
    }


}
