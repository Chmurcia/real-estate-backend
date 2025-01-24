package cloud.uwu.realestatebackend.dtos.profile.profileRate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRateDTO {

    @NotNull(message = "evaluator_id must be defined")
    @JsonProperty("evaluator_id")
    private UUID evaluatorId;

    @NotBlank(message = "title must be defined")
    @Size(min = 2, max = 50, message = "title of a rate must contain between 2 and 50 characters")
    private String title;

    @NotBlank(message = "description must be defined")
    @Size(min = 2, max = 100, message = "description of a rate must contain between 2 and 100 characters")
    private String description;

    @NotNull(message = "rate must be defined")
    @Max(value = 10, message = "rate must value be at most 10")
    @Min(value = 0, message = "rate value must be at least 0")
    private Integer rate;
}
