package com.busesred.consumer.location.repository;

import com.busesred.consumer.location.entity.BusLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BusLocationRepository extends JpaRepository<BusLocation, Long> {
    List<BusLocation> findByBusId(String busId);
    List<BusLocation> findByRoute(String route);
    List<BusLocation> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
