package cloud.uwu.realestatebackend.entities.property.neighbourhood;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyNeighbourhoodReview {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    @ManyToOne
    @JoinColumn(name = "property_neighbourhood_id", nullable = false, referencedColumnName = "id")
    private PropertyNeighbourhood neighbourhood;

    private UUID reviewerId;

    private Integer rate;

    private String description;

    private Integer noiseLevelRate;

    private Integer qualityLevelRate;

    private Integer safetyLevelRate;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
