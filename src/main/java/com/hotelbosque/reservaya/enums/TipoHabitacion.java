package com.hotelbosque.reservaya.enums;

// Crear en paquete com.hotelbosque.reservaya.enums
public enum TipoHabitacion {
    INDIVIDUAL("INDIVIDUL"),
    DOBLE("DOBLE"),
    SUITE_FAMILIAR("SUITE FAMILIAR"),
    SUITE_EJECUTIVA("SUIT EJECUTIVA");
    
    private final String descripcion;
    
    TipoHabitacion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

