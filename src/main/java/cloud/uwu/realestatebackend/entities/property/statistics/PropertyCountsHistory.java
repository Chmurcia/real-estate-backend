package cloud.uwu.realestatebackend.entities.property.statistics;

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
public class PropertyCountsHistory {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    private Integer visits;

    private Integer likes;

    private Integer dislikes;

    @ManyToOne
    @JoinColumn(name = "property_statistics_id", nullable = false, referencedColumnName = "id")
    private PropertyStatistics propertyStatistics;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
