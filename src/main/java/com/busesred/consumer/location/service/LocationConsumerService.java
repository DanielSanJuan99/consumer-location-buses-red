package com.busesred.consumer.location.service;

import com.busesred.consumer.location.entity.BusLocation;
import com.busesred.consumer.location.model.LocationMessage;
import com.busesred.consumer.location.repository.BusLocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LocationConsumerService {

    private final BusLocationRepository locationRepository;

    public LocationConsumerService(BusLocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @RabbitListener(queues = "${rabbitmq.queue.location}")
    public void receiveLocation(LocationMessage locationMessage) {
        try {
            log.info("Mensaje recibido de RabbitMQ: {}", locationMessage);
            
            // Convertir mensaje a entidad
            BusLocation busLocation = new BusLocation();
            busLocation.setBusId(locationMessage.getBusId());
            busLocation.setLatitude(locationMessage.getLatitude());
            busLocation.setLongitude(locationMessage.getLongitude());
            busLocation.setTimestamp(locationMessage.getTimestamp());
            busLocation.setRoute(locationMessage.getRoute());
            busLocation.setSpeed(locationMessage.getSpeed());
            
            // Log para debugging
            log.debug("Entidad antes de guardar - busId: {}, lat: {}, lon: {}, route: {}, speed: {}",
                busLocation.getBusId(), busLocation.getLatitude(), busLocation.getLongitude(),
                busLocation.getRoute(), busLocation.getSpeed());
            
            // Guardar en base de datos
            BusLocation saved = locationRepository.save(busLocation);
            log.info("Ubicación guardada en Oracle DB con ID: {}", saved.getLocationId());
            
        } catch (Exception e) {
            log.error("Error al procesar mensaje de ubicación: ", e);
            throw new RuntimeException("Error al guardar ubicación en BD", e);
        }
    }
}
