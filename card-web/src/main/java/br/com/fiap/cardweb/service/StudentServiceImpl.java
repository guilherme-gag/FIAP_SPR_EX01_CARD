package br.com.fiap.cardweb.service;

import br.com.fiap.cardweb.dto.StudentCreateUpdateDTO;
import br.com.fiap.cardweb.dto.StudentDTO;
import br.com.fiap.cardweb.entity.StudentEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.fiap.cardweb.repository.StudentRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository repository;
    private ObjectMapper objectMapper;
    public StudentServiceImpl(StudentRepository repository,
                           ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }
    @Override
    public StudentDTO create(StudentCreateUpdateDTO createUpdateDTO) {
        StudentEntity entity = new StudentEntity(createUpdateDTO);
        StudentEntity savedEntity = repository.save(entity);
        return new StudentDTO(savedEntity);
    }
    @Override
    public StudentDTO findById(Long id) {
        StudentEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        StudentDTO dto = objectMapper.convertValue(entity, StudentDTO.class);
        return dto;
    }
    @Override
    public List<StudentDTO> listAll(String name) {
        List<StudentEntity> list;
        if (name == null) {
            list = repository.findAll();
        } else {
            list = repository.findAllByNameContaining(name);
        }
        return list.stream()
                .map(StudentDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public StudentDTO update(Long id, StudentCreateUpdateDTO createUpdateDTO) {
        StudentEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setName(createUpdateDTO.getName());
        entity.setDocument(createUpdateDTO.getDocument());
        StudentEntity savedEntity = repository.save(entity);
        return new StudentDTO(savedEntity);
    }
    @Override
    public void delete(Long id) {repository.deleteById(id);}
}
