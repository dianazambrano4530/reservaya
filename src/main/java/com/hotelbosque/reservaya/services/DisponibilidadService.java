package com.hotelbosque.reservaya.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotelbosque.reservaya.models.Habitacion;
import com.hotelbosque.reservaya.repositories.HabitacionRepository;
import com.hotelbosque.reservaya.repositories.ReservaRepository;

@Service
public class DisponibilidadService {
    
    @Autowired
    private HabitacionRepository habitacionRepository;
    
    @Autowired
    private ReservaRepository reservaRepository;
    
    public List<Habitacion> buscarHabitacionesDisponibles(LocalDate fechaLlegada, 
                                                         LocalDate fechaSalida, 
                                                         int cantidadHuespedes) {
        List<Habitacion> habitacionesPorCapacidad = 
            habitacionRepository.findAvailableRoomsForGuests(cantidadHuespedes);
        
        return habitacionesPorCapacidad.stream()
            .filter(habitacion -> 
                reservaRepository.findReservasConflictivas(
                    habitacion.getId(), fechaLlegada, fechaSalida).isEmpty())
            .collect(Collectors.toList());
    }
}
