package cloud.uwu.realestatebackend.entities.property.statistics;

import cloud.uwu.realestatebackend.entities.property.Property;
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
public class PropertyCounts {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    private Integer visits;

    private Integer likes;

    private Integer dislikes;

    @OneToOne(mappedBy = "counts")
    private PropertyStatistics propertyStatistics;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
