package com.busesred.consumer.location.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID")
    private Long locationId;
    
    @Column(name = "BUS_ID", nullable = false, length = 50)
    private String busId;
    
    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;
    
    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;
    
    @Column(name = "TIMESTAMP_RECEIVED", nullable = false)
    private LocalDateTime timestamp;
    
    @Column(name = "ROUTE", length = 50)
    private String route;
    
    @Column(name = "SPEED")
    private Double speed;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
