package br.com.fiap.cardweb.controller;

import br.com.fiap.cardweb.dto.StudentCreateUpdateDTO;
import br.com.fiap.cardweb.dto.StudentDTO;
import br.com.fiap.cardweb.dto.StudentFilterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.cardweb.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentDTO> list(StudentFilterDTO filterDTO) {
        return service.listAll(filterDTO.getName());
    }

    @GetMapping("{id}")
    public StudentDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO create(@RequestBody StudentCreateUpdateDTO createUpdateTeamDTO) {
        return service.create(createUpdateTeamDTO);
    }

    @PutMapping("{id}")
    public StudentDTO update(@PathVariable Long id,
                             @RequestBody StudentCreateUpdateDTO createUpdateTeamDTO) {
        return service.update(id, createUpdateTeamDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
