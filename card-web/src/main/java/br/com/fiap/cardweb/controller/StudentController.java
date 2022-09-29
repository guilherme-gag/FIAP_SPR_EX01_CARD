package br.com.fiap.cardweb.controller;

import br.com.fiap.cardweb.dto.StudentDTO;
import br.com.fiap.cardweb.dto.StudentFilterDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.cardweb.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> list(StudentFilterDTO teamFilterDTO) {
        return studentService.listAll(teamFilterDTO.getName());
    }


}
