package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.dto.AccommodationDto;
import mk.ukim.finki.emt.lab.model.exceptions.AccommodationNotFound;
import mk.ukim.finki.emt.lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab.service.AccommodationService;
import mk.ukim.finki.emt.lab.service.CountryService;
import mk.ukim.finki.emt.lab.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final CountryService countryService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService, CountryService countryService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.countryService = countryService;
    }


    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, AccommodationDto accommodation) {
        return accommodationRepository.findById(id)
                .map(existingAccommodation -> {
                    if(accommodation.getCategory() != null){
                        existingAccommodation.setCategory(accommodation.getCategory());
                    }
                    if(accommodation.getName() != null){
                        existingAccommodation.setName(accommodation.getName());
                    }
                    if(accommodation.getNumRooms() != null){
                        existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    }
                    if(accommodation.getCategory() != null){
                        existingAccommodation.setCategory(accommodation.getCategory());
                    }
                    if(accommodation.getHost() != null && hostService.findById(accommodation.getHost()).isPresent()){
                        existingAccommodation.setHost(hostService.findById(accommodation.getHost()).get());
                    }

                    return accommodationRepository.save(existingAccommodation);
                });
    }

    @Override
    public Optional<Accommodation> save(AccommodationDto accommodation) {
        if(accommodation.getName() != null &&
                accommodation.getCategory() != null &&
                accommodation.getNumRooms() != null &&
                hostService.findById(accommodation.getHost()).isPresent()
        ){
            return Optional.of(
                    accommodationRepository.save(new Accommodation(accommodation.getName(),
                            accommodation.getCategory(),
                            hostService.findById(accommodation.getHost()).get(),
                            accommodation.getNumRooms()))
            );
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public void markAsTaken(Long id) {
        Accommodation accommodation = this.findById(id).orElseThrow(() -> new AccommodationNotFound(id));
        int n = accommodation.getNumRooms() - 1;

        if(n < 0)
            n = 0;

        accommodation.setNumRooms(n);
        accommodationRepository.save(accommodation);
    }

}
