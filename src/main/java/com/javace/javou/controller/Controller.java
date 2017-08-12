package com.javace.javou.controller;

import com.javace.javou.model.Cidade;
import com.javace.javou.model.Estado;
import com.javace.javou.service.CidadeService;
import com.javace.javou.service.EstadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.System.nanoTime;

@RestController
public class Controller {

    private static Logger log = LoggerFactory.getLogger(CidadeService.class);

    private final CidadeService cidadeService;
    private final EstadoService estadoService;

    @Autowired
    public Controller(CidadeService cidadeService, EstadoService estadoService) {
        this.cidadeService = cidadeService;
        this.estadoService = estadoService;
    }

    @RequestMapping(path = "/estado/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Estado> getEstados() {
        long start = nanoTime();
        List<Estado> estados = estadoService.getAll();
        long end = nanoTime();
        log.info("getEstados call took {} ms.", TimeUnit.NANOSECONDS.toMillis(end - start));
        return estados;
    }

    @RequestMapping(path = "/cidade/{siglaEstado}", method = RequestMethod.GET)
    @ResponseBody
    public List<Cidade> getCidadeByEstado(@PathVariable("siglaEstado") String sigla) {
        long start = nanoTime();
        List<Cidade> cidades = cidadeService.findBySiglaEstado(sigla);
        long end = nanoTime();
        log.info("getCidadeByEstado call took {} ms.", TimeUnit.NANOSECONDS.toMillis(end - start));
        return cidades;
    }

    @RequestMapping(path = "/clear", method = RequestMethod.GET)
    @ResponseBody
    public String clearCache() {
        estadoService.clearCache();
        cidadeService.clearCache();
        return "OK";
    }
}
