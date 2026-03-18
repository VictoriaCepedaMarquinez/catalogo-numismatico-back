package com.service;

import com.entities.Moneda;
import com.repositories.MonedaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonedaService {
    private final MonedaRepository monedaRepository;

    public MonedaService(MonedaRepository monedaRepository) {
        this.monedaRepository = monedaRepository;
    }

    public void save(Moneda moneda) {
        monedaRepository.save(moneda);
    }

    public void delete(Long idMoneda) {
        monedaRepository.deleteById(idMoneda);
    }

    public List<Moneda> findAll() {
        return monedaRepository.findAll();
    }

    public Moneda findByMonedaId(Long monedaId) {
        return monedaRepository.findById(monedaId).orElse(null);
    }

}
