
package com.cryptoapp.service;

import com.cryptoapp.model.User;
import com.cryptoapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        Optional<User> exists = userRepository.findByCorreo(user.getCorreo());
        if (exists.isPresent()) throw new RuntimeException("Correo ya registrado");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public User update(Long id, User updated) {
        User u = findById(id);
        u.setNombre(updated.getNombre());
        u.setCorreo(updated.getCorreo());
        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            u.setPassword(passwordEncoder.encode(updated.getPassword()));
        }
        u.setRol(updated.getRol());
        u.setSaldoVirtual(updated.getSaldoVirtual());
        return userRepository.save(u);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
