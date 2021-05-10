package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Voorstelling;

import javax.validation.Valid;
import java.util.List;

public interface VoorstellingService {
Voorstelling findbyid(long id);
List<Voorstelling>findAllById();
   List<Voorstelling> findAll();
 boolean createBoeking(long id,int aantal);
}
