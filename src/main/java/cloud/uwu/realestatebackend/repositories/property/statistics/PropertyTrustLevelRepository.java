package cloud.uwu.realestatebackend.repositories.property.statistics;

import cloud.uwu.realestatebackend.entities.property.statistics.PropertyTrustLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyTrustLevelRepository extends JpaRepository<PropertyTrustLevel, UUID> {
    Page<PropertyTrustLevel> getPropertyTrustLevelsByPropertyStatisticsId(UUID propertyStatisticsId, Pageable pageable);
}
