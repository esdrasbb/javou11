package com.javace.javou.service;

import com.javace.javou.model.Estado;
import com.javace.javou.repository.EstadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "estado")
public class EstadoService {

    private static Logger log = LoggerFactory.getLogger(EstadoService.class);

    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
        log.info("Executing: {}.clearCache();", this.getClass().getSimpleName());
    }

    @Cacheable()
    public List<Estado> getAll() {
        log.info("Executing: {}.getAll();", this.getClass().getSimpleName());
        List<Estado> estados = new ArrayList<>();
        estadoRepository.findAll().forEach(estados::add);
        return estados;
    }

}
