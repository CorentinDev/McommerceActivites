package com.mcommerce.microserviceexpedition.web.controller;


import com.mcommerce.microserviceexpedition.dao.ExpeditionDao;
import com.mcommerce.microserviceexpedition.model.Expedition;
import com.mcommerce.microserviceexpedition.web.exceptions.ExpeditionExistanteException;
import com.mcommerce.microserviceexpedition.web.exceptions.ExpeditionIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ExpeditionController {

    @Autowired
    private ExpeditionDao expeditionDao;


    // Ajouter une expédition
    @PostMapping(value = "/expedition")
    public ResponseEntity<Void> ajouterExpedition(@Valid @RequestBody Expedition expedition) {

        // Verification qu'une expedition pour cette commande n'existe pas déjà
        Expedition expeditionReq = expeditionDao.findByIdCommande(expedition.getIdCommande());
        if(expeditionReq != null) throw new ExpeditionExistanteException("Cette demande d'expedition est déjà faite");

        // Enregistrement du nouvel ordre d'expédition
        Expedition expeditionAdded = expeditionDao.save(expedition);

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



    // Récuperer une expedition par son idCommande
    @GetMapping(value = "/expedition/{idCommande}")
    public Expedition etatExpedition(@PathVariable int idCommande) {

        Expedition expedition = expeditionDao.findByIdCommande(idCommande);

        if(expedition == null)
            throw new ExpeditionIntrouvableException("L'expedition avec l'id " + idCommande + " est INTROUVABLE...");

        return expedition;
    }


    // Modification d'une expedition
    @PutMapping (value = "/expedition")
    public void updateExpedition(@RequestBody Expedition expedition) {

        // Verification que l'expédition avec cette commande existe bien
        etatExpedition(expedition.getIdCommande());

        expeditionDao.save(expedition);
    }



    //Récupérer la liste des expeditions
    @RequestMapping(value = "/expeditions", method = RequestMethod.GET)
    public List<Expedition> listExpeditions() {

        return  expeditionDao.findAll();
    }


}
