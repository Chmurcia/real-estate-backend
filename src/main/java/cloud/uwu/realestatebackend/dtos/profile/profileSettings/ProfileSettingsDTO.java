package cloud.uwu.realestatebackend.dtos.profile.profileSettings;

import cloud.uwu.realestatebackend.entities.profile.profileEnums.Theme;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileSettingsDTO {
    @NotNull(message = "profile_visibility must be defined")
    @JsonProperty("profile_visibility")
    private Boolean profileVisibility;

    @NotNull(message = "theme must be defined")
    @Enumerated(EnumType.STRING)
    private Theme theme;
}
