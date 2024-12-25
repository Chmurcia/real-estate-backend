package cloud.uwu.realestatebackend.dtos.property.multimedia;

import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaImage.PropertyMultimediaImageResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaVideo.PropertyMultimediaVideoDTO;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyMultimediaResponseDTO {
    private UUID id;

    private List<PropertyMultimediaImageResponseDTO> images;

    private List<PropertyMultimediaVideoDTO> videos;
}
