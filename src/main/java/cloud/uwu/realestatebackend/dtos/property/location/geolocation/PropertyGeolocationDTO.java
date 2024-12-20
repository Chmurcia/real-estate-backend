package cloud.uwu.realestatebackend.dtos.property.location.geolocation;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyGeolocationDTO {
    @NotNull(message = "Geolocation must be defined")
    @DecimalMax(value = "90.0", message = "Latitude must be less than or equal to 90")
    @DecimalMin(value = "-90.0", message = "Latitude must be greater than or equal to -90")
    private Double latitude;

    @NotNull(message = "Longitude must be defined")
    @DecimalMax(value = "180.0", message = "Longitude must be less than or equal to 180")
    @DecimalMin(value = "-180.0", message = "Longitude must be greater than or equal to -180")
    private Double longitude;
}
