package cloud.uwu.realestatebackend.dtos.profile.profileAsk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileAskResponseDTO {
    private UUID id;

    @JsonProperty("profile_id")
    private UUID profileId;

    @JsonProperty("ask_title")
    private String askTitle;

    @JsonProperty("ask_description")
    private String askDescription;

    @JsonProperty("ask_answer")
    private String askAnswer;
}
