package es.eriktorr.example.petclinic.owners.web;

import es.eriktorr.example.petclinic.owners.database.OwnersRepository;
import es.eriktorr.example.petclinic.owners.model.Owner;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners")
public class OwnersRestController {

    private final OwnersRepository ownersRepository;

    public OwnersRestController(OwnersRepository ownersRepository) {
        this.ownersRepository = ownersRepository;
    }

    @GetMapping(value = "{ownerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Owner> findOwnerBy(@PathVariable("ownerId") int ownerId) {
        val owner = ownersRepository.findOwnerBy(ownerId);
        return owner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
