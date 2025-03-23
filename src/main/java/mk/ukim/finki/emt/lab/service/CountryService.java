package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.dto.CountryDto;

import java.util.*;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto country);
    Optional<Country> update(Long id, CountryDto country);
    void deleteById(Long id);

}
