package cloud.uwu.realestatebackend.entities.property.multimedia;

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
public class PropertyMultimedia {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    @OneToOne(mappedBy = "propertyMultimedia")
    private Property property;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "property_multimedia_id")
    private List<PropertyMultimediaImage> images = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "property_multimedia_id")
    private List<PropertyMultimediaVideo> videos = new ArrayList<>();

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
