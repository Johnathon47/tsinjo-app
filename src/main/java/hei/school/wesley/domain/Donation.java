package hei.school.wesley.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Donation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Embedded private Donor donor;

  @Embedded private Payment payment;

  @Enumerated(EnumType.STRING)
  private PaymentStatus paymentStatus;

  private String pspPaymentId;
}
