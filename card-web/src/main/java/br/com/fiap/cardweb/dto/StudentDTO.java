package br.com.fiap.cardweb.dto;

import br.com.fiap.cardweb.entity.StudentEntity;

public class StudentDTO {
    private long id;
    private String name;
    private String document;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public StudentDTO(StudentEntity entity) {
        this.id = entity.getId();
        this.document = entity.getDocument();
        this.name = entity.getName();
    }
}
