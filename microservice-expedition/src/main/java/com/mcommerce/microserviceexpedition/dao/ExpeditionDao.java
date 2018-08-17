package com.mcommerce.microserviceexpedition.dao;

import com.mcommerce.microserviceexpedition.model.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExpeditionDao extends JpaRepository<Expedition, Integer> {

    Expedition findById(int id);

    List<Expedition> findAll();

}
