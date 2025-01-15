package cloud.uwu.realestatebackend.dtos.property.property;

import cloud.uwu.realestatebackend.entities.property.propertyEnum.OfferStatus;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.PropertyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDTO {
    @NotNull(message = "property_type must be defined")
    @JsonProperty("property_type")
    private PropertyType propertyType;

    @NotNull(message = "offer_status must be defined")
    @JsonProperty("offer_status")
    private OfferStatus offerStatus;

    @NotBlank(message = "title must be defined")
    @Size(min = 3, max = 100, message = "title must contain between 3 and 100 characters")
    private String title;

    @NotBlank(message = "description must be defined")
    @Size(max = 2000, message = "description must contain at most 2000 characters")
    private String description;

    @NotNull(message = "price must be defined")
    @Min(value = 0, message = "price must be at least 0")
    private Double price;
}
