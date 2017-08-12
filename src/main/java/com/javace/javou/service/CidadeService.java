package com.javace.javou.service;

import com.javace.javou.model.Cidade;
import com.javace.javou.model.Estado;
import com.javace.javou.repository.CidadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "cidade")
public class CidadeService {

    private static Logger log = LoggerFactory.getLogger(CidadeService.class);

    private final CidadeRepository cidadeRepository;
    private final EstadoService estadoService;

    @Autowired
    public CidadeService(CidadeRepository cidadeRepository, EstadoService estadoService) {
        this.cidadeRepository = cidadeRepository;
        this.estadoService = estadoService;
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
        log.info("Executing: {}.clearCache();", this.getClass().getSimpleName());
    }

    @Cacheable() //condition = "#instrument.equals('trombone')"
    public List<Cidade> findBySiglaEstado(String sigla) {
        log.info("Executing: {}.findBySiglaEstado(\"{}\");", this.getClass().getSimpleName(), sigla);
        Optional<Estado> estado = estadoService.getAll().stream().filter(e -> e.getSigla().equals(sigla.toUpperCase())).findFirst();
        return estado.isPresent() ? cidadeRepository.findByEstado(estado.get()) : Collections.emptyList();
    }

}
