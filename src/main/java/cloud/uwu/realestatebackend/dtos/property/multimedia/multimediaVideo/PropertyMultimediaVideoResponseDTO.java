package cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaVideo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyMultimediaVideoResponseDTO {
    private UUID id;

    @JsonProperty("video_title")
    private String videoTitle;

    @JsonProperty("video_url")
    private String videoURL;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;
}
