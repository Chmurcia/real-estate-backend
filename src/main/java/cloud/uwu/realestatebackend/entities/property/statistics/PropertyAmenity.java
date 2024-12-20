package cloud.uwu.realestatebackend.entities.property.statistics;

import cloud.uwu.realestatebackend.entities.property.propertyEnum.AmenityType;
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
public class PropertyAmenity {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    private AmenityType amenityType;

    @ManyToOne
    @JoinColumn(name = "property_statistics_id", nullable = false, referencedColumnName = "id")
    private PropertyStatistics propertyStatistics;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
