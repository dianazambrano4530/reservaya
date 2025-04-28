package com.hotelbosque.reservaya.controllers;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hotelbosque.reservaya.models.Habitacion;
import com.hotelbosque.reservaya.services.DisponibilidadService;

@Controller
public class DisponibilidadController {
    
    @Autowired
    private DisponibilidadService disponibilidadService;
    
    @GetMapping("/")
    public String index() {
        return "disponibilidad";
    }
    
    @GetMapping("/consultar-disponibilidad")
    public String consultarDisponibilidad(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaLlegada,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSalida,
            @RequestParam int cantidadHuespedes,
            Model model) {
        
        List<Habitacion> habitacionesDisponibles = 
            disponibilidadService.buscarHabitacionesDisponibles(
                fechaLlegada, fechaSalida, cantidadHuespedes);
        
        model.addAttribute("habitaciones", habitacionesDisponibles);
        model.addAttribute("fechaLlegada", fechaLlegada);
        model.addAttribute("fechaSalida", fechaSalida);
        model.addAttribute("cantidadHuespedes", cantidadHuespedes);
        
        return "disponibilidad";
    }
}
