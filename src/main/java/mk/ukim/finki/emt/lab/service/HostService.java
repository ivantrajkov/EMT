package mk.ukim.finki.emt.lab.service;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.dto.HostDto;

import java.util.*;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
    Optional<Host> save(HostDto host);
    Optional<Host> update(Long id, HostDto host);
    void deleteById(Long id);


}
