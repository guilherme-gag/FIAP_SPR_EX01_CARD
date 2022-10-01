package br.com.fiap.cardweb.repository;

import br.com.fiap.cardweb.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository  extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findByStudentId(long studentId);
}
