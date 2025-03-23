package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.dto.AccommodationDto;

import java.util.*;

public interface AccommodationService {
    //todo: CRUD operations
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> update(Long id, AccommodationDto accommodation);
    Optional<Accommodation> save(AccommodationDto accommodation);
    void deleteById(Long id);
    void markAsTaken(Long id);

}
