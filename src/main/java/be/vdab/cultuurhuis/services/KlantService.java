package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;

import java.util.Optional;

public interface KlantService {
    Optional<Klant> findById(long id);
    Optional<Klant>findBygebruikersNaam(String naam);
//  public  void create(Klant klant, String klantURL);
    boolean boekReservatie(long id, Reservatie reservatie);
public  void create(Klant klant);
}
