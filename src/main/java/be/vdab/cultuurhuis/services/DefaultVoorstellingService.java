package be.vdab.cultuurhuis.services;


import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.exceptions.VoorstellingNietGevondenException;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DefaultVoorstellingService implements VoorstellingService {
    private final VoorstellingRepository voorstellingRepository;

    public DefaultVoorstellingService(VoorstellingRepository voorstellingRepository) {
        this.voorstellingRepository = voorstellingRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Voorstelling findbyid(long id) {
        return voorstellingRepository.findById(id).orElseThrow(VoorstellingNietGevondenException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Voorstelling> findAllById() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Voorstelling> findAll() {
        return voorstellingRepository.findAll();
    }
}