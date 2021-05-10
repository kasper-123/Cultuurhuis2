package be.vdab.cultuurhuis.services;


import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.exceptions.KlantNietGevondenException;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
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
    @Transactional
    public boolean create(Klant klant) {
        try{
            klantRepository.save(klant);
        return true;
        }catch (RuntimeException ex){
            throw  ex;
        }
    }

@Override
@Transactional
public boolean boekReservatie(long id,@Valid Reservatie reservatie) {
        
        if (klantRepository.findById(id).isPresent()){
             var klant= klantRepository.findById(id).get();
         
            try {
              ;
                klantRepository.save(klant);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else throw new KlantNietGevondenException();

        return false;
}


}
