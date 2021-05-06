package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.repositories.GenreRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultGenreService implements GenreService{
    private final GenreRepository repository;

    public DefaultGenreService(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll(){
        System.out.println(repository.findAll());
        return repository.findAll(Sort.by("naam"));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> findById(long id) {
        
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> findByNaam(String naam)
    {
        return repository.findByNaam(naam);


    }
}
