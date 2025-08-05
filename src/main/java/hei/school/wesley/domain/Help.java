package hei.school.wesley.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Help {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Embedded private Beneficiary beneficiary;

  @Embedded private Payment payment;

  private String description; // ici la description de l'accident
}
