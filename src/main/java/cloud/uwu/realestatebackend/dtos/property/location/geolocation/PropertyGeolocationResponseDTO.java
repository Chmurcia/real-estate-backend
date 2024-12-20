package cloud.uwu.realestatebackend.dtos.property.location.geolocation;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyGeolocationResponseDTO {
    private UUID id;

    private Double latitude;

    private Double longitude;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
