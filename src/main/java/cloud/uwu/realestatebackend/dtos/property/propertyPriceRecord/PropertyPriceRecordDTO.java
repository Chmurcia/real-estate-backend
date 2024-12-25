package cloud.uwu.realestatebackend.dtos.property.propertyPriceRecord;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyPriceRecordDTO {
    @NotNull(message = "price must be defined")
    @Min(value = 0, message = "price must be at least 0")
    private Double price;
}
