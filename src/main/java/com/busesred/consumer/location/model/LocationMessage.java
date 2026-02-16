package com.busesred.consumer.location.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationMessage implements Serializable {

    @JsonProperty("id_bus")
    private String busId;

    @JsonProperty("latitud")
    private Double latitude;

    @JsonProperty("longitud")
    private Double longitude;

    @JsonProperty("marca_tiempo")
    private LocalDateTime timestamp;

    @JsonProperty("ruta")
    private String route;

    @JsonProperty("velocidad")
    private Double speed;
}
