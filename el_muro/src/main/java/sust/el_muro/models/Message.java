package sust.el_muro.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "messages")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  Long id;

  @Column(nullable = false)
  String message;

  @Column
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  Date created_at;

  @Column
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  Date updated_at;

  // al momento de crear un mensaje en la base de datos, se fija automáticamente
  // la fecha actual
  @PrePersist
  public void onCreate() {
    this.created_at = new Date();
  }

  @PreUpdate
  public void onUpdate() {
    this.updated_at = new Date();
  }

}
