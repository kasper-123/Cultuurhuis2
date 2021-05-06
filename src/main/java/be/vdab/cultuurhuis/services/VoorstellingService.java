package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Voorstelling;

import java.util.List;

public interface VoorstellingService {
Voorstelling findbyid(long id);
List<Voorstelling>findAllById();
   List<Voorstelling> findAll();
 //  List<Voorstelling> findByGenreId(Long naam);
}
