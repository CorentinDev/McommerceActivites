package com.mcommerce.microserviceexpedition.web.controller;


import com.mcommerce.microserviceexpedition.dao.ExpeditionDao;
import com.mcommerce.microserviceexpedition.model.Expedition;
import com.mcommerce.microserviceexpedition.web.exceptions.ExpeditionIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class ExpeditionController {

    @Autowired
    private ExpeditionDao expeditionDao;


    // Ajouter une expédition produit
    @PostMapping(value = "/expedition")
    public ResponseEntity<Void> ajouterExpedition(@Valid @RequestBody Expedition product) {

        Expedition expeditionAdded = expeditionDao.save(product);

        if(expeditionAdded == null){
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(expeditionAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }



    // Récuperer une expedition par son id
    @GetMapping(value = "/expedition/{id}")
    public Expedition afficherUneExpedition(@PathVariable int id) {

        Expedition expedition = expeditionDao.findById(id);

        if(expedition == null)
            throw new ExpeditionIntrouvableException("L'expedition avec l'id " + id + " est INTROUVABLE...");

        return expedition;
    }


    // Modification d'une expedition
    @PutMapping (value = "/expedition")
    public void updateExpedition(@RequestBody Expedition expedition) {
        expeditionDao.save(expedition);
    }



    //Récupérer la liste des expeditions
    @RequestMapping(value = "/expeditions", method = RequestMethod.GET)
    public List<Expedition> listExpeditions() {

        return  expeditionDao.findAll();
    }


}
