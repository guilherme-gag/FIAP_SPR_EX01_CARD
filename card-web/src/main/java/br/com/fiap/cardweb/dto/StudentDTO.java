package br.com.fiap.cardweb.dto;

import br.com.fiap.cardweb.entity.StudentEntity;

public class StudentDTO {
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

    public StudentDTO(StudentEntity studentEntity) {
        this.document = studentEntity.getDocument();
        this.name = studentEntity.getName();
    }
}
