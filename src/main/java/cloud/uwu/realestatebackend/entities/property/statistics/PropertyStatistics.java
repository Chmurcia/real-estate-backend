package cloud.uwu.realestatebackend.entities.property.statistics;

import cloud.uwu.realestatebackend.entities.property.Property;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.ZonedDateTime;
import java.util.ArrayList;
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

    private Double rating;

    private ZonedDateTime lastVisitedAt;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "propertyStatistics", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyAccessibility> propertyAccessibilities = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "propertyStatistics", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyAmenity> propertyAmenities = new ArrayList<>();

    @OneToOne(mappedBy = "statistics")
    private Property property;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "propertyStatistics", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyTrustLevel> propertyTrustLevels = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private PropertyCounts counts;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
