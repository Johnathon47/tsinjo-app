package hei.school.wesley.endpoint.rest.controller;

import hei.school.wesley.domain.Donation;
import hei.school.wesley.domain.PaymentStatus;
import hei.school.wesley.repository.DonationRepository;
import hei.school.wesley.repository.HelpRepository;
import hei.school.wesley.service.VolaPaymentService;
import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TsinjoController {

  private final DonationRepository donationRepository;
  private final HelpRepository helpRepository;
  private final VolaPaymentService volaPaymentService;

  public TsinjoController(
      DonationRepository donationRepository,
      HelpRepository helpRepository,
      VolaPaymentService volaPaymentService) {
    this.donationRepository = donationRepository;
    this.helpRepository = helpRepository;
    this.volaPaymentService = volaPaymentService;
  }

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("donations", donationRepository.findAll());
    model.addAttribute("helps", helpRepository.findAll());
    model.addAttribute("newDonation", new Donation());
    return "index";
  }

  @PostMapping("/donate")
  public String donate(@ModelAttribute Donation donation) {
    donation.getPayment().setDate(LocalDate.now());
    donation.setPaymentStatus(PaymentStatus.VERIFYING);
    donationRepository.save(donation);

    // Appel asynchrone à Vola pour la vérification
    volaPaymentService.submitForVerification(donation);

    return "redirect:/";
  }
}
