package cloud.uwu.realestatebackend.mappers.property.multimedia;

import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaVideo.PropertyMultimediaVideoDTO;
import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaVideo.PropertyMultimediaVideoResponseDTO;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimediaVideo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropertyMultimediaVideoMapper {
    @Mapping(source = "propertyMultimedia.id", target = "propertyMultimediaId")
    PropertyMultimediaVideoDTO propertyMultimediaVideoToPropertyMultimediaVideoDTO(PropertyMultimediaVideo propertyMultimediaVideo);

    PropertyMultimediaVideoResponseDTO propertyMultimediaVideoToPropertyMultimediaVideoResponseDTO(PropertyMultimediaVideo propertyMultimediaVideo);
}
