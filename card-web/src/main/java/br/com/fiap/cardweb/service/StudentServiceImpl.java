package br.com.fiap.cardweb.service;

import br.com.fiap.cardweb.dto.StudentDTO;
import br.com.fiap.cardweb.entity.StudentEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import br.com.fiap.cardweb.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;
    private ObjectMapper objectMapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                           ObjectMapper objectMapper) {
        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
    }
    @Override
    public List<StudentDTO> listAll(String name) {
        List<StudentEntity> list;
        list = studentRepository.findAll();
        return list.stream()
                .map(StudentDTO::new)
                .collect(Collectors.toList());
    }
}
