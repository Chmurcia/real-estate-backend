package cloud.uwu.realestatebackend.entities.property.statistics;

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
public class PropertyStatistics {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    private ZonedDateTime lastVisitedAt;

    @OneToMany(mappedBy = "propertyStatistics")
    private List<PropertyAccessibility> propertyAccessibilities;

    @OneToMany(mappedBy = "propertyStatistics")
    private List<PropertyAmenity> propertyAmenities;

    @OneToOne(mappedBy = "statistics")
    private Property property;

    @OneToMany(mappedBy = "propertyStatistics")
    private List<PropertyTrustLevel> propertyTrustLevels;

    @OneToOne
    private PropertyCounts counts;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
