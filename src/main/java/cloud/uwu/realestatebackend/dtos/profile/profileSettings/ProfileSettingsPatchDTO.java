package cloud.uwu.realestatebackend.dtos.profile.profileSettings;

import cloud.uwu.realestatebackend.entities.profile.profileEnums.Theme;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileSettingsPatchDTO {
    private Boolean profileVisibility;

    @Enumerated(EnumType.STRING)
    private Theme theme;
}
