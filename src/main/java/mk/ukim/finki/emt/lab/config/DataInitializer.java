package mk.ukim.finki.emt.lab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.enums.AccommodationCategory;
import mk.ukim.finki.emt.lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.repository.HostRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    //IS AVAILABLE AT http://localhost:8080/swagger-ui/index.html
    private final AccommodationRepository accommodationRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;

    public DataInitializer(AccommodationRepository accommodationRepository, CountryRepository countryRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
    }

    @PostConstruct
    public void init() {
        Country macedonia = countryRepository.save(new Country("Macedonia", "Europe"));
        Country germany = countryRepository.save(new Country("Germany", "Europe"));
        Country canada = countryRepository.save(new Country("Canada", "America"));

        Host host1 = hostRepository.save(new Host("Petko", "Petkov", macedonia));
        Host host2 = hostRepository.save(new Host("Marko", "Markov", germany));
        Host host3 = hostRepository.save(new Host("Petre", "Petrov", canada));

        accommodationRepository.save(new Accommodation("Hotel A", AccommodationCategory.HOTEL, host1, 30));
        accommodationRepository.save(new Accommodation("Apartment B", AccommodationCategory.APARTMENT, host2, 10));
        accommodationRepository.save(new Accommodation("Flat C", AccommodationCategory.FLAT, host3, 5));


    }

}
