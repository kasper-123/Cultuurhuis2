package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Klant;

import java.util.Optional;

public interface KlantService {
    Optional<Klant> findById(long id);
    Optional<Klant>findBygebruikersNaam(String naam);
    void create(Klant klant);


}
