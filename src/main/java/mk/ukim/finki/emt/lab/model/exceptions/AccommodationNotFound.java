package mk.ukim.finki.emt.lab.model.exceptions;

public class AccommodationNotFound extends RuntimeException{
    public AccommodationNotFound(Long id) {
        super("The accommodation with the following id:" + id + " not found!");
    }
}
