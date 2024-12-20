package cloud.uwu.realestatebackend.entities.property;

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
public class PropertyPriceRecord {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false, referencedColumnName = "id")
    private Property property;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
