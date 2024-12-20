package cloud.uwu.realestatebackend.entities.property.multimedia;

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
public class PropertyMultimedia {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    @OneToOne(fetch = FetchType.LAZY)
    private Property property;

    @OneToMany(fetch = FetchType.LAZY)
    private List<PropertyMultimediaImage> images;

    @OneToMany(fetch = FetchType.LAZY)
    private List<PropertyMultimediaVideo> videos;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
