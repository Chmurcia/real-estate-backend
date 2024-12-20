package cloud.uwu.realestatebackend.dtos.property.location.location;

import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyLocationResponseDTO {
    private String country;

    private String state;

    private String city;

    @JsonProperty("zip_code")
    private String zipCode;

    private String address;

    @JsonProperty("geolocation")
    private PropertyGeolocationDTO geolocationDTO;
}
