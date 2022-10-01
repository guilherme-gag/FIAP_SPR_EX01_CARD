package br.com.fiap.cardweb.repository;

import br.com.fiap.cardweb.entity.CardEntity;
import br.com.fiap.cardweb.entity.StudentEntity;
import br.com.fiap.cardweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository  extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByStudentId(long studentId);
}
