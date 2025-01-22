package cloud.uwu.realestatebackend.repositories.property;

import cloud.uwu.realestatebackend.entities.property.PropertyPriceRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyPriceRecordRepository extends JpaRepository<PropertyPriceRecord, UUID> {
    Page<PropertyPriceRecord> getPropertyPriceRecordsByPropertyIdOrderByCreatedAtAsc(UUID propertyId, Pageable pageable);
}
