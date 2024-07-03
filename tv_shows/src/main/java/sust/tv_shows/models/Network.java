package sust.tv_shows.models;

import jakarta.persistence.Column;
// Hibernate uso de os decoradores de relacion
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "networks")
public class Network {
  @Id
  @GeneratedValue
  Long id;

  @Column
  String name;

  @OneToMany(mappedBy = "network") // @decoradorqueespecificatipoderelacion( = "objetoInstanciadetipotabla")
  List<Show> shows; // Listta<deobjetosshow> objeto de clase Show

}
