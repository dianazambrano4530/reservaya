package com.hotelbosque.reservaya.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotelbosque.reservaya.models.Habitacion;
import com.hotelbosque.reservaya.models.Reserva;
import com.hotelbosque.reservaya.repositories.HabitacionRepository;
import com.hotelbosque.reservaya.repositories.ReservaRepository;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;
    
    @Autowired
    private HabitacionRepository habitacionRepository;
    
    public boolean verificarDisponibilidad(Long habitacionId, 
                                          LocalDate fechaLlegada, 
                                          LocalDate fechaSalida) {
        return reservaRepository.findReservasConflictivas(
                habitacionId, fechaLlegada, fechaSalida).isEmpty();
    }
    
    public Reserva crearReserva(Long habitacionId, 
                               String nombreCliente, 
                               String emailCliente,
                               String telefonoCliente,
                               LocalDate fechaLlegada, 
                               LocalDate fechaSalida,
                               int cantidadHuespedes) {
        
        if (!verificarDisponibilidad(habitacionId, fechaLlegada, fechaSalida)) {
            throw new RuntimeException("La habitación no está disponible para las fechas seleccionadas");
        }
        
        Habitacion habitacion = habitacionRepository.findById(habitacionId)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        
        Reserva reserva = new Reserva();
        reserva.setHabitacion(habitacion);
        reserva.setNombreCliente(nombreCliente);
        reserva.setEmailCliente(emailCliente);
        reserva.setTelefonoCliente(telefonoCliente);
        reserva.setFechaLlegada(fechaLlegada);
        reserva.setFechaSalida(fechaSalida);
        reserva.setCantidadHuespedes(cantidadHuespedes);
        
        // Cálculo del monto total
        long diasEstancia = ChronoUnit.DAYS.between(fechaLlegada, fechaSalida);
        double montoTotal = habitacion.getPrecio() * diasEstancia;
        reserva.setMontoTotal(montoTotal);
        
        reserva.setPagado(false);
        reserva.setEstadoReserva("Pendiente");
        
        return reservaRepository.save(reserva);
    }
    
    public Reserva confirmarPago(Long reservaId) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        
        reserva.setPagado(true);
        reserva.setEstadoReserva("Confirmada");
        
        return reservaRepository.save(reserva);
    }
}
