package cloud.uwu.realestatebackend.entities.property;

import cloud.uwu.realestatebackend.entities.property.propertyEnum.ConditionStatus;
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
public class PropertyDetails {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    @OneToOne(mappedBy = "details")
    private Property property;

    private Integer totalFloors;

    private Integer yearBuilt;

    @Enumerated(EnumType.STRING)
    private ConditionStatus conditionStatus;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private PropertyAreas areas;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private PropertyRooms rooms;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
