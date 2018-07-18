package es.eriktorr.example.petclinic.vets.web;

import es.eriktorr.example.petclinic.vets.database.VetsRepository;
import es.eriktorr.example.petclinic.vets.model.Vet;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vets")
public class VetsRestController {

    private final VetsRepository vetsRepository;

    public VetsRestController(VetsRepository vetsRepository) {
        this.vetsRepository = vetsRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Vet> listAll() {
        return vetsRepository.listAll();
    }

}
