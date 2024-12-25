package cloud.uwu.realestatebackend.dtos.property.propertyDetails;

import cloud.uwu.realestatebackend.entities.property.propertyEnum.ConditionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDetailsPatchDTO {

    @Min(value = 1, message = "total_floors must be at least 1")
    @JsonProperty("total_floors")
    private Integer totalFloors;

    @JsonProperty("year_built")
    private Integer yearBuilt;

    @JsonProperty("condition_status")
    private ConditionStatus conditionStatus;
}
