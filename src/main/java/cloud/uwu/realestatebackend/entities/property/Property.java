package cloud.uwu.realestatebackend.entities.property;

import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.property.location.PropertyLocation;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimedia;
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
import java.util.ArrayList;
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

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus;

    private String title;

    private String description;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false, referencedColumnName = "id")
    private Profile profile;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private PropertyDetails details;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private PropertyNeighbourhood neighbourhood;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyDistanceTo> distances  = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyReview> reviews  = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyPriceRecord> pricesHistory = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private PropertyLocation propertyLocation;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private PropertyStatistics statistics;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private PropertyMultimedia propertyMultimedia;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
