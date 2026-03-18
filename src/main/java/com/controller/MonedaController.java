package com.controller;

import com.entities.Moneda;
import com.service.MonedaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/monedas")
public class MonedaController {

    private final MonedaService monedaService;

    public MonedaController(MonedaService monedaService) {
        this.monedaService = monedaService;
    }

    @GetMapping
    public List<Moneda> findAll() {
        return monedaService.findAll();
    }

    @GetMapping("/{id}")
    public Moneda findById(@PathVariable Long id) {
        return monedaService.findByMonedaId(id);
    }

    @PostMapping
    public void save(@RequestBody Moneda moneda) {
        monedaService.save(moneda);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        monedaService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Moneda moneda) {
        moneda.setId(id);
        monedaService.save(moneda);
    }
}