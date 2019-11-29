package be.technofuturtic.javaangularapp.services;

import be.technofuturtic.javaangularapp.models.CategorieEntity;
import be.technofuturtic.javaangularapp.models.DefiEntity;
import be.technofuturtic.javaangularapp.repositories.CategorieRepository;
import be.technofuturtic.javaangularapp.repositories.DefiRepository;
import be.technofuturtic.javaangularapp.utilitaires.DefiEntityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefiServiceImplemented implements DefiService {
    private DefiRepository repo;
    private CategorieRepository repoCat;

    @Autowired
    public DefiServiceImplemented(DefiRepository repository, CategorieRepository repoCate) {
        repo = repository;
        repoCat = repoCate;
    }

    @Override
    public List<DefiEntity> findAll() {
        return (List<DefiEntity>) repo.findAll();
    }

    @Override
    public void desactiverDefi(Integer idDefi) {
        DefiEntity d = repo.findById(idDefi).get();
        d.setActiveDefi(false);
        repo.save(d);
    }

    @Override
    public void activerDefi(Integer idDefi) {
        DefiEntity d = repo.findById(idDefi).get();
        d.setActiveDefi(true);
        repo.save(d);
    }

    @Override
    public void creerDefi(DefiEntityDto nouveauDefi) {
        Optional<CategorieEntity> categorieEntityOptional = this.repoCat.findById(nouveauDefi.getCategorieId());
        DefiEntity defiEntity = new DefiEntity(
                nouveauDefi.getNomDefi(),
                nouveauDefi.getDescDefi(),
                nouveauDefi.getInfobulleDefi(),
                categorieEntityOptional
        );
        //defiEntity.setActiveDefi(true);
        this.repo.save(defiEntity);
    }

    @Override
    public List<DefiEntity> listerActifs() {
        List<DefiEntity> l = findAll();
        List<DefiEntity> a = new ArrayList<>();
        for (int i = 0; i < findAll().size(); i++) {
            if(findAll().get(i).isActiveDefi()) {
                a.add(l.get(i));
            }
        }
        return a;
    }
}
