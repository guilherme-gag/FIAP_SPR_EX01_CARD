package br.com.fiap.cardweb.service;

import br.com.fiap.cardweb.dto.CardCreateDTO;
import br.com.fiap.cardweb.dto.CardDTO;
import br.com.fiap.cardweb.entity.CardEntity;
import br.com.fiap.cardweb.entity.StudentEntity;
import br.com.fiap.cardweb.repository.CardRepository;
import br.com.fiap.cardweb.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CardServiceImpl implements CardService {

    private StudentRepository studentRepository;
    private CardRepository repository;
    private ObjectMapper objectMapper;

    public CardServiceImpl(CardRepository repository,
                           StudentRepository studentRepository,
                           ObjectMapper objectMapper) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public CardDTO create(long studentId, CardCreateDTO createUpdateDTO) {
        CardEntity entity = new CardEntity(createUpdateDTO);
        StudentEntity studentEntity = studentRepository.findById(studentId).get();
        entity.setStudent(studentEntity);
        CardEntity savedEntity = repository.save(entity);
        return new CardDTO(savedEntity);
    }

    @Override
    public CardDTO findById(Long id) {
        CardEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CardDTO dto = objectMapper.convertValue(entity, CardDTO.class);
        return dto;
    }

    @Override
    public List<CardDTO> listAll(long studentId) {
        List<CardEntity> list = repository.findByStudentId(studentId);
        return list.stream()
                .map(CardDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
