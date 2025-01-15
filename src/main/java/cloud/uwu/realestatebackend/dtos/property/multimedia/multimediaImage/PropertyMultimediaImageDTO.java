package cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaImage;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyMultimediaImageDTO {

    @Size(min = 1, max = 100, message = "image_title must contain between 1 and 100 characters")
    @NotNull(message = "image_title must be defined")
    @JsonProperty("image_title")
    private String imageTitle;

    @NotNull(message = "image_url must be defined")
    @JsonProperty("image_url")
    private String imageURL;
}
