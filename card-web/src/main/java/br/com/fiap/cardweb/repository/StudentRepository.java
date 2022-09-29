package br.com.fiap.cardweb.repository;

import br.com.fiap.cardweb.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
