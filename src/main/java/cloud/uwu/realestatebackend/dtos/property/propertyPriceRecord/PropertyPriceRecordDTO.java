package cloud.uwu.realestatebackend.dtos.property.propertyPriceRecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyPriceRecordDTO {
    @NotNull(message = "property_id must be defined")
    @JsonProperty("property_id")
    private UUID propertyId;

    @NotNull(message = "price must be defined")
    @Min(value = 0, message = "price must be at least 0")
    private Double price;
}
