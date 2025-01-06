package cloud.uwu.realestatebackend.repositories.property.statistics;

import cloud.uwu.realestatebackend.entities.property.statistics.PropertyAmenity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface PropertyAmenityRepository extends JpaRepository<PropertyAmenity, UUID> {
    List<PropertyAmenity> getPropertyAmenitiesByPropertyStatisticsId(UUID propertyStatisticsId);
}
