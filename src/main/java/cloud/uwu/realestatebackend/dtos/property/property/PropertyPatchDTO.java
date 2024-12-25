package cloud.uwu.realestatebackend.dtos.property.property;

import cloud.uwu.realestatebackend.entities.property.propertyEnum.OfferStatus;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.PropertyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyPatchDTO {
    @JsonProperty("property_type")
    private PropertyType propertyType;

    @JsonProperty("offer_status")
    private OfferStatus offerStatus;

    @Size(min = 3, max = 100, message = "title must contain between 3 and 100 characters")
    private String title;

    @Size(max = 2000, message = "description must contain at most 2000 characters")
    private String description;

    @Min(value = 0, message = "price must be at least 0")
    private BigDecimal price;
}
