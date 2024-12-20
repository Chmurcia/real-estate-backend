package cloud.uwu.realestatebackend.dtos.profile.profileAsk;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileAskPatchDTO {
    @Size(min = 2, max = 50, message = "Title of an ask must contain between 2 and 50 characters")
    @JsonProperty("ask_title")
    private String askTitle;

    @Size(min = 2, max = 100, message = "Description of an ask must contain between 2 and 150 characters")
    @JsonProperty("ask_description")
    private String askDescription;

    @Size(min = 2, max = 200, message = "Description of an ask must contain between 2 and 200 characters")
    @JsonProperty("ask_answer")
    private String askAnswer;
}
