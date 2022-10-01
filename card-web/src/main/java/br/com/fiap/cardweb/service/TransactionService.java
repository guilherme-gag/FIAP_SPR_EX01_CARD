package br.com.fiap.cardweb.service;

import br.com.fiap.cardweb.dto.CardCreateDTO;
import br.com.fiap.cardweb.dto.CardDTO;
import br.com.fiap.cardweb.dto.TransactionCreateDTO;
import br.com.fiap.cardweb.dto.TransactionDTO;
import br.com.fiap.cardweb.repository.CardRepository;
import br.com.fiap.cardweb.repository.StudentRepository;
import br.com.fiap.cardweb.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public interface TransactionService {

    TransactionDTO create(long studentId,long cardId, TransactionCreateDTO createUpdateDTO);

    TransactionDTO findById(Long id);

    List<TransactionDTO> listAll(long studentId);

    List<TransactionDTO> listAll(long studentId,long cardId);
}
