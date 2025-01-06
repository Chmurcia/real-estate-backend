package cloud.uwu.realestatebackend.dtos.other.post;

import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationDTO;
import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationDTO {
    @Valid
    @NotNull
    @JsonProperty("property_location")
    PropertyLocationDTO propertyLocationDTO;

    @Valid
    @NotNull
    @JsonProperty("property_geolocation")
    PropertyGeolocationDTO propertyGeolocationDTO;
}
