package cloud.uwu.realestatebackend.repositories.property.statistics;

import cloud.uwu.realestatebackend.entities.property.statistics.PropertyCountsHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyCountsHistoryRepository extends JpaRepository<PropertyCountsHistory, UUID> {
    Page<PropertyCountsHistory> getPropertyCountsHistoriesByPropertyStatisticsIdOrderByCreatedAtAsc(UUID id, Pageable pageable);
}
