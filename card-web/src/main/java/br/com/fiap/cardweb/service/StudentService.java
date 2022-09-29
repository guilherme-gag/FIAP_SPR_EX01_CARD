package br.com.fiap.cardweb.service;

import br.com.fiap.cardweb.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> listAll(String name);
}
