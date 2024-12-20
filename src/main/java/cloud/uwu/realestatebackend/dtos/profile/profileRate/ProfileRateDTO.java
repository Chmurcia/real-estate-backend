package cloud.uwu.realestatebackend.dtos.profile.profileRate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRateDTO {
    @NotNull(message = "Id of profile must be defined")
    @JsonProperty("profile_id")
    private UUID profileId;

    @NotNull(message = "Id of evaluator must be defined")
    @JsonProperty("evaluator_id")
    private UUID evaluatorId;

    @Size(min = 2, max = 50, message = "Title of a rate must contain between 2 and 50 characters")
    private String title;

    @Size(min = 2, max = 100, message = "Title of a rate must contain between 2 and 100 characters")
    private String description;

    @Max(value = 10, message = "Rate must value be at most 10")
    @Min(value = 0, message = "Rate value must be at least 0")
    private Integer rate;
}
