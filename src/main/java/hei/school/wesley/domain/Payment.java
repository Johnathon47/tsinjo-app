package hei.school.wesley.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Payment {

  private LocalDate date;
  private Double amount;
  private String method;
}
