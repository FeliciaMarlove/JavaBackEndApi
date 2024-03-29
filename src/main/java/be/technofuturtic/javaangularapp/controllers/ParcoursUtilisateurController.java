package be.technofuturtic.javaangularapp.controllers;

import be.technofuturtic.javaangularapp.models.DefiEntity;
import be.technofuturtic.javaangularapp.models.ParcoursUtilisateurLiaison;
import be.technofuturtic.javaangularapp.models.UtilisateurEntity;
import be.technofuturtic.javaangularapp.repositories.UtilisateurRepository;
import be.technofuturtic.javaangularapp.services.ParcoursService;
import be.technofuturtic.javaangularapp.services.ParcoursUtilisateurLiaisonService;
import be.technofuturtic.javaangularapp.utilitaires.ParcoursUtilisateurDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/utilparc", method = {RequestMethod.POST, RequestMethod.GET})
@CrossOrigin(origins = {"http://localhost:4200"})
public class ParcoursUtilisateurController {
    private final ParcoursUtilisateurLiaisonService service;

    public ParcoursUtilisateurController(ParcoursUtilisateurLiaisonService service) {
        this.service = service;
    }

    @GetMapping
    public List<ParcoursUtilisateurDto> list() {
        return service.findAll();
    }

    @PostMapping("/start/{idutil}/{idparcours}")
    public Boolean commencerParcours(@PathVariable("idutil") Long idUtillisateur,
                                  @PathVariable("idparcours") Integer idParcours) {
        return this.service.commencerParcours(idParcours, idUtillisateur);
    }

    @PostMapping("/defi/{idutil}")
    public DefiEntity voirDefiDuJour(@PathVariable("idutil") Long idUtil) {
        return this.service.voirDefiDuJour(idUtil);
    }
}
