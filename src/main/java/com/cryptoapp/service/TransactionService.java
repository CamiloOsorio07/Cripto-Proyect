
package com.cryptoapp.service;

import com.cryptoapp.model.Transaction;
import com.cryptoapp.model.User;
import com.cryptoapp.repository.TransactionRepository;
import com.cryptoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository txRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Transaction registrarTransaccion(Transaction tx) {
        User user = userRepository.findById(tx.getUserId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if ("compra".equalsIgnoreCase(tx.getTipo())) {
            double total = tx.getCantidad() * tx.getPrecio();
            if (user.getSaldoVirtual() >= total) {
                user.setSaldoVirtual(user.getSaldoVirtual() - total);
            } else {
                throw new RuntimeException("Saldo insuficiente");
            }
        } else if ("venta".equalsIgnoreCase(tx.getTipo())) {
            double total = tx.getCantidad() * tx.getPrecio();
            user.setSaldoVirtual(user.getSaldoVirtual() + total);
        } else {
            throw new RuntimeException("Tipo de transaccion invalido. Use 'compra' o 'venta'.");
        }

        userRepository.save(user);
        return txRepository.save(tx);
    }

    public List<Transaction> historial(Long userId) {
        return txRepository.findByUserId(userId);
    }
}
