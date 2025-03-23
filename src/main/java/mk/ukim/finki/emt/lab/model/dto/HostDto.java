package mk.ukim.finki.emt.lab.model.dto;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class HostDto {
    private String name;
    private String surname;
    @ManyToOne
    private Long country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
