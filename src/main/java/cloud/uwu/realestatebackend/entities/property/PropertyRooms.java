package cloud.uwu.realestatebackend.entities.property;

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
public class PropertyRooms {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    @OneToOne(mappedBy = "rooms")
    private PropertyDetails propertyDetails;

    // Basic rooms
    private Integer totalRooms;

    private Integer totalBedrooms;

    private Integer totalKitchens;

    private Integer totalBathrooms;

    private Integer totalLivingRooms;

    private Integer totalDiningRooms;

    private Integer totalBalconies;

    // Utility rooms
    private Integer totalGarages;

    private Integer totalBasements;

    private Integer totalAttics;

    private Integer totalStorageRooms;

    private Integer totalClosets;

    // Special rooms
    private Integer totalTheaterRooms;

    private Integer totalSaunas;

    private Integer totalHomeOffices;

    private Integer totalGyms;

    private Integer totalLibraries;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
