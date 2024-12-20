package cloud.uwu.realestatebackend.dtos.profile.profileSettings;

import cloud.uwu.realestatebackend.entities.profile.profileEnums.Theme;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileSettingsPatchDTO {
    @JsonProperty("profile_visibility")
    private Boolean profileVisibility;

    @Enumerated(EnumType.STRING)
    private Theme theme;
}
