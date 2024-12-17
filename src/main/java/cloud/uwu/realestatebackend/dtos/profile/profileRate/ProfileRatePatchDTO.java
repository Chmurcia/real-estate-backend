package cloud.uwu.realestatebackend.dtos.profile.profileRate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRatePatchDTO {
    private String title;

    private String description;

    private Integer rate;
}
