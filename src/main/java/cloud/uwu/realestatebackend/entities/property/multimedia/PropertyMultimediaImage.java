package cloud.uwu.realestatebackend.entities.property.multimedia;

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
public class PropertyMultimediaImage {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    private String imageTitle;

    private String imageURL;

    @ManyToOne
    private PropertyMultimedia propertyMultimedia;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
