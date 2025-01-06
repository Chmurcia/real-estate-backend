package cloud.uwu.realestatebackend.repositories.property;

import cloud.uwu.realestatebackend.entities.property.PropertyReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PropertyReviewRepository extends JpaRepository<PropertyReview, UUID> {
    List <PropertyReview> getPropertyReviewsByPropertyId(UUID propertyId);
}
