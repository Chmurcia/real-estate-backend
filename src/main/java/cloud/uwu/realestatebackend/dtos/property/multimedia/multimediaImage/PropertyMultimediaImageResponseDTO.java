package cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaImage;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyMultimediaImageResponseDTO {
    private UUID id;

    @JsonProperty("image_title")
    private String imageTitle;

    @JsonProperty("image_url")
    private String imageURL;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;
}
