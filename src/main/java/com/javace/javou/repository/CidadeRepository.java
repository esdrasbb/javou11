package com.javace.javou.repository;

import com.javace.javou.model.Cidade;
import com.javace.javou.model.Estado;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CidadeRepository extends CrudRepository<Cidade, Long> {

    List<Cidade> findByEstado(Estado estado);

}
