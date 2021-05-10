package be.vdab.cultuurhuis.services;


import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.exceptions.ReservatieNietGemaaktException;
import be.vdab.cultuurhuis.exceptions.VoorstellingNietGevondenException;
import be.vdab.cultuurhuis.repositories.ReservatieRepository;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DefaultReservatieService implements ReservatieService {
   private final ReservatieRepository reservatieRepository;
private final VoorstellingRepository voorstellingRepository;
    public DefaultReservatieService(ReservatieRepository reservatieRepository, VoorstellingRepository voorstellingRepository) {
        this.reservatieRepository = reservatieRepository;
        this.voorstellingRepository = voorstellingRepository;
    }
    
    @Override
    @Transactional
    public boolean create(Reservatie reservatie) {
       try {
           var voorstelling = voorstellingRepository.findById(reservatie.getVoorstelling().getId()).get();
           voorstelling.verminderVrijePlaatsen(reservatie.getPlaatsen());
           voorstellingRepository.save(voorstelling);
           reservatieRepository.save(reservatie);
           return true;
       }catch (ReservatieNietGemaaktException ex){
           throw new ReservatieNietGemaaktException(ex);
       }
    }
}



