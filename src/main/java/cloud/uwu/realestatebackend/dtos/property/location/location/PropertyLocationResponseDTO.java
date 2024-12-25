package cloud.uwu.realestatebackend.dtos.property.location.location;

import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyLocationResponseDTO {
    private UUID id;

    private String country;

    private String state;

    private String city;

    @JsonProperty("zip_code")
    private String zipCode;

    private String address;

    @JsonProperty("geolocation")
    private PropertyGeolocationDTO geolocationDTO;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
