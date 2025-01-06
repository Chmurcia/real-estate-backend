package cloud.uwu.realestatebackend.repositories.property.statistics;

import cloud.uwu.realestatebackend.entities.property.statistics.PropertyCounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyCountsRepository extends JpaRepository<PropertyCounts, UUID> {
    PropertyCounts getPropertyCountsByPropertyStatisticsId(UUID propertyStatisticsId);
}
