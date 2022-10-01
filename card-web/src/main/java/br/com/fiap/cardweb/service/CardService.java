package br.com.fiap.cardweb.service;

import br.com.fiap.cardweb.dto.CardCreateDTO;
import br.com.fiap.cardweb.dto.CardDTO;

import java.util.List;

public interface CardService {
    CardDTO create(long studentId, CardCreateDTO createUpdateDTO);
    CardDTO findById(Long id);
    List<CardDTO> listAll(long studentId);
    void delete(Long id);
}
