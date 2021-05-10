package be.vdab.cultuurhuis.repositories;


import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import javax.validation.constraints.Positive;
import java.util.List;

public interface VoorstellingRepository extends JpaRepository<Voorstelling,Long> {

    List<Voorstelling> findByGenre(Genre genre);
    //@Modifying
    //@Query(value = "update Voorstelling v set v.vrijeplaatsen = (v.vrijeplaatsen - :aantal) where v.id = :id")
   // int boeking(@Param("id")long id,@Param("aantal") int aantal);
}
