package com.cryptoapp.controller;

import com.cryptoapp.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/crypto")
@CrossOrigin
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping("/price/{id}")
    public ResponseEntity<Map<String, Map<String, Object>>> getPrice(@PathVariable String id) {
        Map<String, Map<String, Object>> resp = cryptoService.getCryptoPrice(id);
        return ResponseEntity.ok(resp);
    }
}
