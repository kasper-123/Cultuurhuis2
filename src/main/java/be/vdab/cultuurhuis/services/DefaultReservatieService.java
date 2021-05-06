package be.vdab.cultuurhuis.services;

/**
@Service
@Transactional
public class DefaultReservatieService implements ReservatieService {
   private final ReservatieRepository reservatieRepository;

    public DefaultReservatieService(ReservatieRepository reservatieRepository) {
        this.reservatieRepository = reservatieRepository;
    }

    @Override
    @Transactional
    public List<Reservatie> FindByKlantId(long id) {
        return reservatieRepository.findByKlant_Id(id);
    }

    @Override
    public void create(Reservatie reservatie) {
reservatieRepository.save(reservatie);
    }
}
**/


