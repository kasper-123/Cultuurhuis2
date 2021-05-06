package be.vdab.cultuurhuis.services;


import be.vdab.cultuurhuis.domain.Reservatie;

import java.util.List;

public interface ReservatieService {
    List<Reservatie>FindByKlantId(long id);
    void create(Reservatie reservatie);
}
