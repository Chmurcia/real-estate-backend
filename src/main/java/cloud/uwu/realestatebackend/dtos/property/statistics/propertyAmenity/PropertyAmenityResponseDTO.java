package cloud.uwu.realestatebackend.dtos.property.statistics.propertyAmenity;

import cloud.uwu.realestatebackend.entities.property.propertyEnum.AmenityType;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyAmenityResponseDTO {
    private UUID id;

    private AmenityType amenityType;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
