package cloud.uwu.realestatebackend.entities.property.neighbourhood;

import cloud.uwu.realestatebackend.entities.property.Property;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyNeighbourhood {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    @OneToOne(mappedBy = "neighbourhood")
    private Property property;

    private Integer noiseLevel;

    private Integer qualityLevel;

    private Integer safetyLevel;

    @OneToMany(mappedBy = "neighbourhood", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyNeighbourhoodReview> reviews;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
