package be.technofuturtic.javaangularapp.services;

import be.technofuturtic.javaangularapp.models.UtilisateurEntity;

import java.util.List;

public interface UtilisateurService {
    public List<UtilisateurEntity> findAll();
}