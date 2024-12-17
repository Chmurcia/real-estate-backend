package cloud.uwu.realestatebackend.dtos.profile.profileAsk;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileAskPatchDTO {
    @Size(min = 2, max = 50, message = "Title of an ask must contain between 2 and 50 characters")
    private String askTitle;

    @Size(min = 2, max = 100, message = "Description of an ask must contain between 2 and 150 characters")
    private String askDescription;

    @Size(min = 2, max = 200, message = "Description of an ask must contain between 2 and 200 characters")
    private String askAnswer;
}
