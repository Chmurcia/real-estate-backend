package cloud.uwu.realestatebackend.entities.property;

import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.property.neighbourhood.PropertyNeighbourhood;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.OfferStatus;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.PropertyType;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    private PropertyType propertyType;

    private OfferStatus offerStatus;

    private String title;

    private String description;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false, referencedColumnName = "id")
    private Profile profile;

    @OneToOne(fetch = FetchType.LAZY)
    private PropertyDetails details;

    @OneToOne(fetch = FetchType.LAZY)
    private PropertyNeighbourhood neighbourhood;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyDistanceTo> distances;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyReview> reviews;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyPriceRecord> pricesHistory;

    @OneToOne
    private PropertyStatistics statistics;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
