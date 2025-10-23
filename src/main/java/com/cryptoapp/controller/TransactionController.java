package com.cryptoapp.controller;

import com.cryptoapp.model.Transaction;
import com.cryptoapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import com.cryptoapp.dto.TransactionDTO;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionService txService;

    @PostMapping
    public ResponseEntity<Transaction> crear(@Valid @RequestBody TransactionDTO dto) {
        Transaction tx = new Transaction();
        tx.setUserId(dto.getUserId());
        tx.setCrypto(dto.getCrypto());
        tx.setCantidad(dto.getCantidad());
        tx.setPrecio(dto.getPrecio());
        tx.setTipo(dto.getTipo());
        return ResponseEntity.ok(txService.registrarTransaccion(tx));
    }

    @GetMapping("/{userId}")
    public List<Transaction> historial(@PathVariable Long userId) {
        return txService.historial(userId);
    }
}
