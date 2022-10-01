package br.com.fiap.cardweb.entity;

import br.com.fiap.cardweb.dto.StudentCreateUpdateDTO;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "TB_STUDENT")
@EntityListeners(AuditingEntityListener.class)
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String document;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StudentEntity() {
    }

    public StudentEntity(StudentCreateUpdateDTO createUpdateStudentDTO) {
        this.name = createUpdateStudentDTO.getName();
        this.document = createUpdateStudentDTO.getDocument();
    }
}
