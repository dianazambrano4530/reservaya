package com.hotelbosque.reservaya.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hotelbosque.reservaya.models.Habitacion;
import com.hotelbosque.reservaya.models.Reserva;
import com.hotelbosque.reservaya.repositories.HabitacionRepository;
import com.hotelbosque.reservaya.services.ReservaService;

@Controller
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;
    
    @Autowired
    private HabitacionRepository habitacionRepository;
    
    @GetMapping("/realizar-reserva")
    public String formularioReserva(
            @RequestParam Long habitacionId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaLlegada,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSalida,
            @RequestParam int cantidadHuespedes,
            Model model) {
        
        Habitacion habitacion = habitacionRepository.findById(habitacionId)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        
        model.addAttribute("habitacion", habitacion);
        model.addAttribute("fechaLlegada", fechaLlegada);
        model.addAttribute("fechaSalida", fechaSalida);
        model.addAttribute("cantidadHuespedes", cantidadHuespedes);
        
        return "reserva";
    }
    
    @PostMapping("/confirmar-reserva")
    public String confirmarReserva(
            @RequestParam Long habitacionId,
            @RequestParam String nombreCliente,
            @RequestParam String emailCliente,
            @RequestParam String telefonoCliente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaLlegada,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSalida,
            @RequestParam int cantidadHuespedes,
            Model model) {
        
        Reserva reserva = reservaService.crearReserva(
            habitacionId, nombreCliente, emailCliente, telefonoCliente,
            fechaLlegada, fechaSalida, cantidadHuespedes);
        
        // Simular pago (en un sistema real, se integraría con pasarela de pagos)
        reserva = reservaService.confirmarPago(reserva.getId());
        
        model.addAttribute("reserva", reserva);
        return "confirmacion";
    }
}

