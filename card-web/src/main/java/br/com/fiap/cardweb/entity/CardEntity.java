package br.com.fiap.cardweb.entity;

import br.com.fiap.cardweb.dto.CardCreateDTO;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "TB_CARD")
@EntityListeners(AuditingEntityListener.class)
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
    private String label;
    private String number;
    private int expMonth;
    private int expYear;
    private String cvv;
    private String brand;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public CardEntity() {

    }

    public CardEntity(CardCreateDTO dto) {
        this.label = dto.getLabel();
        this.number = dto.getNumber();
        this.expMonth = dto.getExpMonth();
        this.expYear = dto.getExpYear();
        this.cvv = dto.getCvv();
        this.brand = dto.getBrand();
    }
}
