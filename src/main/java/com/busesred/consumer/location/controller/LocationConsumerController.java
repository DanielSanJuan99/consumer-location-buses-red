package com.busesred.consumer.location.controller;

import com.busesred.consumer.location.entity.BusLocation;
import com.busesred.consumer.location.repository.BusLocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consumer-location")
@Slf4j
public class LocationConsumerController {

    private final BusLocationRepository locationRepository;

    public LocationConsumerController(BusLocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("/locations")
    public ResponseEntity<List<BusLocation>> getAllLocations() {
        log.info("Consultando todas las ubicaciones");
        return ResponseEntity.ok(locationRepository.findAll());
    }

    @GetMapping("/locations/bus/{busId}")
    public ResponseEntity<List<BusLocation>> getLocationsByBusId(@PathVariable String busId) {
        log.info("Consultando ubicaciones del bus: {}", busId);
        return ResponseEntity.ok(locationRepository.findByBusId(busId));
    }

    @GetMapping("/locations/route/{route}")
    public ResponseEntity<List<BusLocation>> getLocationsByRoute(@PathVariable String route) {
        log.info("Consultando ubicaciones de la ruta: {}", route);
        return ResponseEntity.ok(locationRepository.findByRoute(route));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("estado", "ACTIVO");
        response.put("servicio", "consumer-location-buses-red");
        response.put("marca_tiempo", LocalDateTime.now().toString());
        response.put("estado_bd", "Conectada");
        return ResponseEntity.ok(response);
    }
}
