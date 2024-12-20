package cloud.uwu.realestatebackend.repositories.property.statistics;

import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyStatisticsRepository extends JpaRepository<PropertyStatistics, UUID> {
}
