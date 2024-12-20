package cloud.uwu.realestatebackend.dtos.property.location.location;

import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationDTO;
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

    private String zipCode;

    private String address;

    private PropertyGeolocationDTO geolocationDTO;
}
