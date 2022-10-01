package br.com.fiap.cardweb.service;

import br.com.fiap.cardweb.dto.TransactionCreateDTO;
import br.com.fiap.cardweb.dto.TransactionDTO;

import java.io.Writer;
import java.util.List;

public interface TransactionService {

    TransactionDTO create(long studentId,long cardId, TransactionCreateDTO createUpdateDTO);

    TransactionDTO findById(Long id);

    List<TransactionDTO> listAll(long studentId);

    List<TransactionDTO> listAll(long studentId,long cardId);

    void writeToCsv(Writer writer, Long id);

    void writeListToCsv(Writer writer, Long studentId, Long cardId);

}
