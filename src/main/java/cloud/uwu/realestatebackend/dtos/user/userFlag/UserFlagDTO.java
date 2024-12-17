package cloud.uwu.realestatebackend.dtos.user.userFlag;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFlagDTO {

    @NotNull(message = "isVerified must be defined")
    private Boolean isVerified;

    @NotNull(message = "isBanned must be defined")
    private Boolean isBanned;

    @NotNull(message = "isMuted must be defined")
    private Boolean isMuted;


}
