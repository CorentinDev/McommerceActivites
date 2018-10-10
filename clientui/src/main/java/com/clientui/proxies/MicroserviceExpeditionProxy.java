package com.clientui.proxies;

import com.clientui.beans.ExpeditionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-expedition")
public interface MicroserviceExpeditionProxy {

    @GetMapping(value = "/microservice-expedition/expedition/{idCommande}")
    ExpeditionBean etatExpedition(@PathVariable("idCommande") int idCommande);

    @PostMapping(value = "/microservice-expedition/expedition")
    ResponseEntity<Void> ajouterExpedition(@RequestBody ExpeditionBean expeditionBean);

    @RequestMapping(value = "/microservice-expedition/expeditions", method = RequestMethod.GET)
    List<ExpeditionBean> listExpeditions();
}
