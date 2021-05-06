package be.vdab.cultuurhuis.repositories;


import be.vdab.cultuurhuis.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre,Long> {
Optional<Genre>findByNaam(String naam);

}
