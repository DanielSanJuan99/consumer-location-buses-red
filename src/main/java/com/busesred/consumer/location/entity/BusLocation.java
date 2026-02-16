package com.busesred.consumer.location.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "BUS_LOCATIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bus_location_gen")
    @SequenceGenerator(name = "bus_location_gen", sequenceName = "bus_location_seq", allocationSize = 1)
    @Column(name = "ID_UBICACION")
    @JsonProperty("id_ubicacion")
    private Long locationId;

    @Column(name = "ID_BUS", nullable = false, length = 50)
    @JsonProperty("id_bus")
    private String busId;

    @Column(name = "LATITUD", nullable = false)
    @JsonProperty("latitud")
    private Double latitude;

    @Column(name = "LONGITUD", nullable = false)
    @JsonProperty("longitud")
    private Double longitude;

    @Column(name = "FECHA_RECIBIDO", nullable = false)
    @JsonProperty("fecha_recibido")
    private LocalDateTime timestamp;

    @Column(name = "RUTA", length = 50)
    @JsonProperty("ruta")
    private String route;

    @Column(name = "VELOCIDAD")
    @JsonProperty("velocidad")
    private Double speed;

    @Column(name = "CREADO_EN", nullable = false)
    @JsonProperty("creado_en")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
