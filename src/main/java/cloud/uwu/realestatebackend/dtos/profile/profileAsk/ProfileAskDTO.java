package cloud.uwu.realestatebackend.dtos.profile.profileAsk;

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
    @NotNull(message = "Id of profile must be defined")
    private UUID profileId;

    @NotNull(message = "Title of an ask must be defined")
    @Size(min = 2, max = 50, message = "Title of an ask must contain between 2 and 50 characters")
    private String askTitle;

    @NotNull(message = "Description of an ask must be defined")
    @Size(min = 2, max = 100, message = "Description of an ask must contain between 2 and 150 characters")
    private String askDescription;

    @NotNull(message = "Answer of an ask must be defined")
    @Size(min = 2, max = 200, message = "Description of an ask must contain between 2 and 200 characters")
    private String askAnswer;
}
