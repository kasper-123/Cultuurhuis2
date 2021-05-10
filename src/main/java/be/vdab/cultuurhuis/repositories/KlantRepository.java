package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;


public interface KlantRepository extends JpaRepository<Klant,Long> {


    Optional<Klant> findByGebruikersnaam(String naam);
    

}
