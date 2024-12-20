package cloud.uwu.realestatebackend.repositories.property.neighbourhood;

import cloud.uwu.realestatebackend.entities.property.neighbourhood.PropertyNeighbourhoodReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyNeighbourhoodReviewRepository extends JpaRepository<PropertyNeighbourhoodReview, UUID> {
}
