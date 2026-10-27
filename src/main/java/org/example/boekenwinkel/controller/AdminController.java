package org.example.boekenwinkel.controller;

import org.example.boekenwinkel.model.Reservation;
import org.example.boekenwinkel.model.User;
import org.example.boekenwinkel.repository.ReservationRepository;
import org.example.boekenwinkel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    // 1. Toon het admin-dashboard met alle gebruikers en reserveringen
    @GetMapping("/dashboard")
    public String adminDashboard(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");

        // Controleer of de ingelogde gebruiker wel een ADMIN is
        if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
            return "redirect:/"; // Terug naar de homepagina als je geen admin bent
        }

        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("reservations", reservationRepository.findAll());
        return "admin-dashboard"; // Dit wordt de HTML-pagina
    }

    // 2. Sla de afhaaldatum en -tijd op voor een specifieke reservering
    @PostMapping("/reservation/update-pickup/{id}")
    public String updatePickupInfo(@PathVariable Long id,
                                   @RequestParam String afhaalDatum,
                                   @RequestParam String afhaalTijd,
                                   HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser != null && "ADMIN".equals(currentUser.getRole())) {
            Reservation reservation = reservationRepository.findById(id).orElse(null);
            if (reservation != null) {
                reservation.setAfhaalDatum(afhaalDatum);
                reservation.setAfhaalTijd(afhaalTijd);
                reservationRepository.save(reservation);
            }
        }
        return "redirect:/admin/dashboard";
    }
}
