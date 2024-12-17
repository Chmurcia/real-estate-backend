package cloud.uwu.realestatebackend.dtos.profile.profileSettings;

import cloud.uwu.realestatebackend.entities.profile.profileEnums.Theme;
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

    private Boolean profileVisibility;

    @Enumerated(EnumType.STRING)
    private Theme theme;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
