package cloud.uwu.realestatebackend.repositories.property.location;

import cloud.uwu.realestatebackend.entities.property.location.PropertyGeolocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyGeolocationRepository extends JpaRepository<PropertyGeolocation, UUID> {
}
