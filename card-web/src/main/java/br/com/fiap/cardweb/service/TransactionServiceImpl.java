package br.com.fiap.cardweb.service;

import br.com.fiap.cardweb.dto.CardCreateDTO;
import br.com.fiap.cardweb.dto.CardDTO;
import br.com.fiap.cardweb.dto.TransactionCreateDTO;
import br.com.fiap.cardweb.dto.TransactionDTO;
import br.com.fiap.cardweb.entity.CardEntity;
import br.com.fiap.cardweb.entity.StudentEntity;
import br.com.fiap.cardweb.entity.TransactionEntity;
import br.com.fiap.cardweb.repository.CardRepository;
import br.com.fiap.cardweb.repository.StudentRepository;
import br.com.fiap.cardweb.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{
    private StudentRepository studentRepository;
    private CardRepository cardRepository;
    private TransactionRepository repository;
    private ObjectMapper objectMapper;

    public TransactionServiceImpl(TransactionRepository repository,
                              StudentRepository studentRepository,
                              CardRepository cardRepository,
                              ObjectMapper objectMapper) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.cardRepository = cardRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public TransactionDTO create(long studentId, long cardId, TransactionCreateDTO createUpdateDTO) {
        TransactionEntity entity = new TransactionEntity(createUpdateDTO);
        StudentEntity studentEntity = studentRepository.findById(studentId).get();
        entity.setStudent(studentEntity);
        CardEntity cardEntity = cardRepository.findById(cardId).get();
        entity.setCard(cardEntity);
        TransactionEntity savedEntity = repository.save(entity);
        return new TransactionDTO(savedEntity);
    }

    @Override
    public TransactionDTO findById(Long id) {
        TransactionEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        TransactionDTO dto = objectMapper.convertValue(entity,TransactionDTO.class);
        return dto;
    }

    @Override
    public List<TransactionDTO> listAll(long studentId) {
        List<TransactionEntity> list = repository.findByStudentId(studentId);
        return list.stream()
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> listAll(long studentId, long cardId) {
        List<TransactionEntity> list = repository.findByStudentId(studentId);
        return list.stream()
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void writeToCsv(Writer writer, Long id) {

        var transactionDTO = findById(id);
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.Builder.create().setDelimiter(';').setHeader("ID", "AMOUNT", "DESCRIPTION").build())) {
            csvPrinter.printRecord(transactionDTO.getId(), transactionDTO.getAmount(),transactionDTO.getDescription());
        } catch (IOException e) {

        }
    }

    @Override
    public void writeListToCsv(Writer writer, Long studentId, Long cardId) {

        List<TransactionDTO> transactionDTOList = listAll(studentId, cardId);
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.Builder.create().setDelimiter(';').setHeader("ID", "AMOUNT", "DESCRIPTION").build())) {
            for (TransactionDTO transactionDTO : transactionDTOList) {
                csvPrinter.printRecord(transactionDTO.getId(), transactionDTO.getAmount(),transactionDTO.getDescription());
            }
        } catch (IOException e) {

        }

    }

}
