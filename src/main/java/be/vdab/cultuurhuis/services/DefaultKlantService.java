package be.vdab.cultuurhuis.services;


import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DefaultKlantService implements KlantService {
    private final KlantRepository klantRepository;

    public DefaultKlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Klant> findById(long id) {
        return klantRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Klant> findBygebruikersNaam(String naam) {
        return klantRepository.findByGebruikersnaam(naam);
    }

    @Override
    public void create(Klant klant) {
        
            klantRepository.save(klant);
        
    }


}
