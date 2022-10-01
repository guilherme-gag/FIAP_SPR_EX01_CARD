package br.com.fiap.cardweb.service;

import br.com.fiap.cardweb.dto.StudentCreateUpdateDTO;
import br.com.fiap.cardweb.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO create(StudentCreateUpdateDTO createUpdateDTO);
    StudentDTO findById(Long id);
    List<StudentDTO> listAll(String nome);
    StudentDTO update(Long id, StudentCreateUpdateDTO createUpdateDTO);
    void delete(Long id);
}
