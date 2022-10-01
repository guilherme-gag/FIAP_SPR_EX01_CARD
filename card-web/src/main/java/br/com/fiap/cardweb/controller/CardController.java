package br.com.fiap.cardweb.controller;

import br.com.fiap.cardweb.dto.*;
import br.com.fiap.cardweb.service.CardService;
import br.com.fiap.cardweb.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students/{studentId}/cards")
public class CardController {
    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping
    public List<CardDTO> list(@PathVariable Long studentId) {
        return service.listAll(studentId);
    }

    @GetMapping("{id}")
    public CardDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTO create(@PathVariable Long studentId, @RequestBody CardCreateDTO cardCreateDTO) {
        return service.create(studentId,cardCreateDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
