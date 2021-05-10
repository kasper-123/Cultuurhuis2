package be.vdab.cultuurhuis.services;


import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.exceptions.KlantNietGevondenException;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import be.vdab.cultuurhuis.restclients.UserClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;

@Service
@Transactional
public class DefaultKlantService implements KlantService {
    private final KlantRepository klantRepository;
private final UserClient userClient;
    public DefaultKlantService(KlantRepository klantRepository, UserClient userClient) {
        this.klantRepository = klantRepository;
        this.userClient = userClient;
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
/**
    @Override
    @Transactional
    public void create(Klant klant,String klantURl) {
        try{
            klantRepository.save(klant);
   userClient.findById(klant.getId());
        }catch (RuntimeException ex){
            throw  ex;
        }
    }
**/
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

@Override
@Transactional
public void create(@Valid Klant klant) {
    klantRepository.save(klant);
}


}
