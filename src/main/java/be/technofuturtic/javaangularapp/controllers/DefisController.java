package be.technofuturtic.javaangularapp.controllers;

import be.technofuturtic.javaangularapp.models.DefiEntity;
import be.technofuturtic.javaangularapp.repositories.DefiRepository;
import be.technofuturtic.javaangularapp.services.DefiService;
import be.technofuturtic.javaangularapp.utilitaires.DefiEntityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/defis", method =  {RequestMethod.POST, RequestMethod.GET})
@CrossOrigin(origins = {"http://localhost:4200"})
public class DefisController {
    private final DefiService service;

    @Autowired
    private DefiRepository defiRepository;

    public DefisController(DefiService service) {
        this.service = service;
    }

    @GetMapping
    public List<DefiEntity> list() {
        return this.service.listerActifs();
    }

    @GetMapping("/all")
    public Iterable<DefiEntity> listAll() {
        return defiRepository.findAll();
    }

    @GetMapping("/{id}")
    public DefiEntity getOne(@PathVariable("id") Integer idDefi) {
        return defiRepository.findByIdDefi(idDefi);
    }

    @PostMapping("/desactiver/{id}")
    public void desactDefi(@PathVariable("id") Integer idDefi) {
        this.service.desactiverDefi(idDefi);
    }

    @PostMapping("/activer/{id}")
    public void actDefi(@PathVariable("id") Integer idDefi) {
        this.service.activerDefi(idDefi);
    }

    @PostMapping("/update/{id}")
    public Boolean modifierDefi(@RequestBody DefiEntityDto defi, @PathVariable("id") Integer idDefiAModifier) {
        return this.service.majDefi(idDefiAModifier, defi);
    }

    @GetMapping("/getcat/{id}")
    public Integer getNumCategorie(@PathVariable("id") Integer x) {
        return service.getIdCategorie(x);
    }

    @PostMapping("/creer")
    public Boolean creerDefi(@RequestBody DefiEntityDto defi) {
        return this.service.creerDefi(new DefiEntityDto(
                defi.getNomDefi(),
                defi.getDescDefi(),
                defi.getInfobulleDefi(),
                defi.getNomCategorie()
        ));
    }
    /* JSON
    {
        "nomDefi" : "Defi 1",
        "descDefi" : "Premier defi",
        "infobulleDefi" : "Defi ajouté dans la catégorie cuisine",
        "categorieId" : 69
    }
     */
}
