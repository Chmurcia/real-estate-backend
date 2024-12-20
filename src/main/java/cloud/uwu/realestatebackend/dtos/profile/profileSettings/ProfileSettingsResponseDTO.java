package cloud.uwu.realestatebackend.dtos.profile.profileSettings;

import cloud.uwu.realestatebackend.entities.profile.profileEnums.Theme;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileSettingsResponseDTO {
    private UUID id;

    @JsonProperty("profile_visibility")
    private Boolean profileVisibility;

    @Enumerated(EnumType.STRING)
    private Theme theme;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
