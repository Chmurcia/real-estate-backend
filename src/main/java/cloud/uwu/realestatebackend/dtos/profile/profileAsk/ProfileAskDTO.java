package cloud.uwu.realestatebackend.dtos.profile.profileAsk;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileAskDTO {
    @NotNull(message = "profile_id must be defined")
    @JsonProperty("profile_id")
    private UUID profileId;

    @NotNull(message = "ask_title must be defined")
    @Size(min = 2, max = 50, message = "ask_title must contain between 2 and 50 characters")
    @JsonProperty("ask_title")
    private String askTitle;

    @NotNull(message = "ask_description must be defined")
    @Size(min = 2, max = 100, message = "ask_description must contain between 2 and 150 characters")
    @JsonProperty("ask_description")
    private String askDescription;

    @NotNull(message = "ask_answer must be defined")
    @Size(min = 2, max = 200, message = "ask_answer must contain between 2 and 200 characters")
    @JsonProperty("ask_answer")
    private String askAnswer;
}
