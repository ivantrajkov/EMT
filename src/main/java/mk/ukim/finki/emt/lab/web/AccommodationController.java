package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.dto.AccommodationDto;
import mk.ukim.finki.emt.lab.model.exceptions.AccommodationNotFound;
import mk.ukim.finki.emt.lab.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<Accommodation> findAll(){
        return accommodationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> findById(@PathVariable Long id){
        return accommodationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Accommodation> save(@RequestBody AccommodationDto accommodation){
        return accommodationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(accommodationService.findById(id).isPresent()){
            accommodationService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Accommodation> update(
            @PathVariable Long id,
            @RequestBody AccommodationDto accommodation)
    {
       return accommodationService.update(id,accommodation)
               .map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/markAsRented/{id}")
    public ResponseEntity<Void> markAsRented(@PathVariable Long id){
        try {
            accommodationService.markAsTaken(id);
            return ResponseEntity.noContent().build();
        } catch (AccommodationNotFound ex){
            return ResponseEntity.notFound().build();
        }
    }

}
