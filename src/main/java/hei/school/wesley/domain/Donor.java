package hei.school.wesley.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Donor {

  private String email;
  private String fullName;
}
