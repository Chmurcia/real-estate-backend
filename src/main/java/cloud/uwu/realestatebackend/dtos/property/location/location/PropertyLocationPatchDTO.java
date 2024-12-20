package cloud.uwu.realestatebackend.dtos.property.location.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyLocationPatchDTO {
    @Size(min = 3, max = 50, message = "Country must contain between 3 and 50 characters")
    private String country;

    @Size(min = 3, max = 50, message = "State must contain between 3 and 70 characters")
    private String state;

    @Size(min = 3, max = 50, message = "City must contain between 3 and 60 characters")
    private String city;

    @Size(min = 5, max = 10, message = "ZipCode must contain between 5 and 10 characters")
    @JsonProperty("zip_code")
    private String zipCode;

    @Size(min = 1, max = 100, message = "Address must contain between 1 and 100 characters")
    private String address;
}
