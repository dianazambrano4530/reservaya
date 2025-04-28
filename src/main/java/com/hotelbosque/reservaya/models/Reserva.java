package com.hotelbosque.reservaya.models;

import java.time.LocalDate;
import jakarta.persistence.*;

/**
 * Clase que representa una reserva en el sistema "RESERVA YA" para el Hotel El Bosque.
 */
@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Habitacion habitacion;
    
    private String nombreCliente;
    private String emailCliente;
    private String telefonoCliente;
    private LocalDate fechaLlegada;
    private LocalDate fechaSalida;
    private int cantidadHuespedes;
    private double montoTotal;
    private boolean pagado;
    private String estadoReserva; // Confirmada, Pendiente, Cancelada

    /**
     * Constructor por defecto
     */
    public Reserva() {
    }

    /**
     * Constructor con parámetros
     */
    public Reserva(Habitacion habitacion, String nombreCliente, String emailCliente, String telefonoCliente,
                  LocalDate fechaLlegada, LocalDate fechaSalida, int cantidadHuespedes,
                  double montoTotal, boolean pagado, String estadoReserva) {
        this.habitacion = habitacion;
        this.nombreCliente = nombreCliente;
        this.emailCliente = emailCliente;
        this.telefonoCliente = telefonoCliente;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.cantidadHuespedes = cantidadHuespedes;
        this.montoTotal = montoTotal;
        this.pagado = pagado;
        this.estadoReserva = estadoReserva;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getCantidadHuespedes() {
        return cantidadHuespedes;
    }

    public void setCantidadHuespedes(int cantidadHuespedes) {
        this.cantidadHuespedes = cantidadHuespedes;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }
    
    /**
     * Calcula la duración de la estancia en días
     */
    public long calcularDiasEstancia() {
        return java.time.temporal.ChronoUnit.DAYS.between(fechaLlegada, fechaSalida);
    }
    
    /**
     * Actualiza el monto total basado en el precio de la habitación y la duración
     */
    public void actualizarMontoTotal() {
        if (habitacion != null) {
            this.montoTotal = habitacion.getPrecio() * calcularDiasEstancia();
        }
    }
    
    /**
     * Confirma la reserva cambiando su estado
     */
    public void confirmarReserva() {
        this.estadoReserva = "Confirmada";
    }
    
    /**
     * Cancela la reserva cambiando su estado
     */
    public void cancelarReserva() {
        this.estadoReserva = "Cancelada";
    }
    
    /**
     * Verifica si hay un conflicto entre esta reserva y un rango de fechas dado
     */
    public boolean hayConflicto(LocalDate inicio, LocalDate fin) {
        return !((fechaSalida.isBefore(inicio) || fechaSalida.isEqual(inicio)) || 
                (fechaLlegada.isAfter(fin) || fechaLlegada.isEqual(fin)));
    }
    
    @Override
    public String toString() {
        return "Reserva [id=" + id + 
               ", habitacion=" + (habitacion != null ? habitacion.getTipo() : "No asignada") + 
               ", cliente=" + nombreCliente + 
               ", fechaLlegada=" + fechaLlegada + 
               ", fechaSalida=" + fechaSalida + 
               ", huéspedes=" + cantidadHuespedes + 
               ", monto=" + montoTotal + 
               ", pagado=" + (pagado ? "Sí" : "No") + 
               ", estado=" + estadoReserva + "]";
    }
}

