package cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaVideo;

import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimedia;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyMultimediaVideoDTO {
    @NotNull(message = "property_multimedia_id must be defined")
    @JsonProperty("property_multimedia_id")
    private UUID propertyMultimediaId;

    @Size(min = 1, max = 100, message = "video_title must contain between 1 and 100 characters")
    @NotNull(message = "video_title must be defined")
    @JsonProperty("video_title")
    private String videoTitle;

    @NotNull(message = "video_url must be defined")
    @JsonProperty("video_url")
    private String videoURL;
}
