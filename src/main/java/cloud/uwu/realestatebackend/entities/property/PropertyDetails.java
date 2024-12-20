package cloud.uwu.realestatebackend.entities.property;

import cloud.uwu.realestatebackend.entities.property.propertyEnum.ConditionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;
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

    @OneToOne
    private PropertyAreas areas;

    @OneToOne
    private PropertyRooms rooms;

    private Integer totalFloors;

    private Integer yearBuilt;

    private ConditionStatus conditionStatus;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
