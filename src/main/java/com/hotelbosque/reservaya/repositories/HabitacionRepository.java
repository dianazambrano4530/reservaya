package com.hotelbosque.reservaya.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hotelbosque.reservaya.models.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    List<Habitacion> findByDisponible(boolean disponible);
    
    @Query("SELECT h FROM Habitacion h WHERE h.capacidad >= ?1 AND h.disponible = true")
    List<Habitacion> findAvailableRoomsForGuests(int cantidadHuespedes);
}
