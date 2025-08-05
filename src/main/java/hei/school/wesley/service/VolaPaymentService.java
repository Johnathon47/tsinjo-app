package hei.school.wesley.service;

import hei.school.wesley.domain.Donation;
import hei.school.wesley.domain.PaymentStatus;
import hei.school.wesley.repository.DonationRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VolaPaymentService {

  private final DonationRepository donationRepository;
  private final RestTemplate restTemplate;

  @Value("${vola.api-key}")
  private String apiKey;

  @Value("${vola.api-url}")
  private String volaApiUrl;

  public VolaPaymentService(DonationRepository donationRepository) {
    this.donationRepository = donationRepository;
    this.restTemplate = new RestTemplate();
  }

  public void submitForVerification(Donation donation) {
    // Juste un log, vu que l'API POST n'est pas nécessaire dans cet examen
    System.out.println("Donation soumise à Vola pour vérification: " + donation.getId());
  }

  public void checkPaymentStatus(Donation donation) {
    String url =
        volaApiUrl
            + "/payment"
            + "?apiKey="
            + apiKey
            + "&payerEmail="
            + donation.getDonor().getEmail()
            + "&pspType=ORANGE_MONEY"
            + "&pspPaymentId="
            + donation.getId(); // À adapter si ce n'est pas l'ID qu'ils donnent à l'examen

    try {
      ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

      if (response.getStatusCode() == HttpStatus.OK) {
        Map<String, Object> body = response.getBody();
        String status = (String) body.get("verificationStatus");

        if ("SUCCEEDED".equals(status)) {
          donation.setPaymentStatus(PaymentStatus.SUCCEEDED);
          donationRepository.save(donation);
          System.out.println("Donation " + donation.getId() + " a été validée.");
        } else if ("FAILED".equals(status)) {
          donation.setPaymentStatus(PaymentStatus.FAILED);
          donationRepository.save(donation);
          System.out.println("Donation " + donation.getId() + " a échoué.");
        } else {
          System.out.println("Donation " + donation.getId() + " en cours de vérification.");
        }
      }
    } catch (Exception e) {
      System.out.println("Erreur lors de la vérification du paiement Vola : " + e.getMessage());
    }
  }
}
