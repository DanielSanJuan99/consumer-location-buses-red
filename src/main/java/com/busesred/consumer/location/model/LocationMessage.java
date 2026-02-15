package com.busesred.consumer.location.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationMessage implements Serializable {
    private String busId;
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
    private String route;
    private Double speed;
}
