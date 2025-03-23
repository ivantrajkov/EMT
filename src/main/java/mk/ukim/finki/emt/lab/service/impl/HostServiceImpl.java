package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.dto.HostDto;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.repository.HostRepository;
import mk.ukim.finki.emt.lab.service.CountryService;
import org.springframework.stereotype.Service;
import mk.ukim.finki.emt.lab.service.HostService;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService{
    private final HostRepository hostRepository;
    private final CountryService countryService;
    private final CountryRepository countryRepository;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService, CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(HostDto host) {
        if(host.getName() != null &&
                host.getCountry() != null &&
                host.getSurname() != null &&
                countryService.findById(host.getCountry()).isPresent()
        ){
            return Optional.of(
                    hostRepository.save(new Host(host.getName(),host.getSurname(),countryService.findById(host.getCountry()).get()))
            );
        }
        return Optional.empty();
    }

    @Override
    public Optional<Host> update(Long id, HostDto host) {
        return hostRepository.findById(id)
                .map(existingHost -> {
                    if(host.getName() != null){
                        existingHost.setName(host.getName());
                    }
                    if(host.getSurname() != null){
                        existingHost.setSurname(host.getSurname());
                    }
                    if(host.getCountry()!=null && countryService.findById(host.getCountry()).isPresent()){
                        existingHost.setCountry(countryService.findById(id).get());
                    }
                    return hostRepository.save(existingHost);
                });
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }
}
