package cloud.uwu.realestatebackend.entities.property.location;

import cloud.uwu.realestatebackend.entities.property.Property;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PropertyLocation {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    private String country;

    private String state;

    private String city;

    @JsonProperty("zip_code")
    private String zipCode;

    private String address;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private PropertyGeolocation geolocation;

    @OneToOne(mappedBy = "propertyLocation")
    private Property property;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
