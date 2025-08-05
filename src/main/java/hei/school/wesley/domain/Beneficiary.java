package hei.school.wesley.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiary {

  private String email;
  private String fullName;
}
