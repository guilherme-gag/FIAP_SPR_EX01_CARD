package br.com.fiap.cardweb.repository;

import br.com.fiap.cardweb.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findAllByNameContaining(String name);
}
