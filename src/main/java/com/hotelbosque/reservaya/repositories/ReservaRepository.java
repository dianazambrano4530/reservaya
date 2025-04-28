package com.hotelbosque.reservaya.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hotelbosque.reservaya.models.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    @Query("SELECT r FROM Reserva r WHERE r.habitacion.id = ?1 AND " +
           "((r.fechaLlegada BETWEEN ?2 AND ?3) OR " +
           "(r.fechaSalida BETWEEN ?2 AND ?3) OR " +
           "(?2 BETWEEN r.fechaLlegada AND r.fechaSalida))")
    List<Reserva> findReservasConflictivas(Long habitacionId, LocalDate fechaInicio, LocalDate fechaFin);
}
