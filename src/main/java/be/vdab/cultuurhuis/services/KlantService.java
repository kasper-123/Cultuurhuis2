package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;

import java.util.Optional;

public interface KlantService {
    Optional<Klant> findById(long id);
    Optional<Klant>findBygebruikersNaam(String naam);
    boolean create(Klant klant);
    boolean boekReservatie(long id, Reservatie reservatie);

}
