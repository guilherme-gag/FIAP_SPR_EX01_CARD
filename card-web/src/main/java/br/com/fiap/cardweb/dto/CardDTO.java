package br.com.fiap.cardweb.dto;

import br.com.fiap.cardweb.entity.CardEntity;
import br.com.fiap.cardweb.entity.StudentEntity;

public class CardDTO {
   private long id;
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

   public CardDTO(CardEntity entity) {
      this.id = entity.getId();
      this.label = entity.getLabel();
      this.number = "**********";
      this.expMonth = entity.getExpMonth();
      this.expYear = entity.getExpYear();
      this.cvv = "***";
      this.brand= entity.getBrand();
   }
   public CardDTO() {
   }
}
