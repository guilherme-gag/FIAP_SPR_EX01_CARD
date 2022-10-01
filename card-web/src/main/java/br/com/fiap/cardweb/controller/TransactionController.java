package br.com.fiap.cardweb.controller;

import br.com.fiap.cardweb.dto.CardCreateDTO;
import br.com.fiap.cardweb.dto.CardDTO;
import br.com.fiap.cardweb.dto.TransactionCreateDTO;
import br.com.fiap.cardweb.dto.TransactionDTO;
import br.com.fiap.cardweb.service.CardService;
import br.com.fiap.cardweb.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students/{studentId}/cards/{cardId}/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransactionDTO> list(@PathVariable Long studentId, @PathVariable long cardId) {
        return service.listAll(studentId, cardId);
    }

    //TODO: Ajustar mapeamento do metodo
    @RequestMapping("students/{studentId}/transactions")
    @GetMapping
    public List<TransactionDTO> list(@PathVariable Long studentId) {
        return service.listAll(studentId);
    }

    @GetMapping("{id}")
    public TransactionDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO create(@PathVariable Long studentId, @PathVariable long cardId, @RequestBody TransactionCreateDTO transactionCreateDTO) {
        return service.create(studentId, cardId, transactionCreateDTO);
    }
}
