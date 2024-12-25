package cloud.uwu.realestatebackend.mappers.property.multimedia;

import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaImage.PropertyMultimediaImageDTO;
import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaImage.PropertyMultimediaImageResponseDTO;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimediaImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropertyMultimediaImageMapper {
    @Mapping(source = "propertyMultimedia.id", target = "propertyMultimediaId")
    PropertyMultimediaImageDTO propertyMultimediaImageToPropertyMultimediaImageDTO(PropertyMultimediaImage propertyMultimediaImage);

    PropertyMultimediaImageResponseDTO propertyMultimediaImageToPropertyMultimediaImageResponseDTO(PropertyMultimediaImage propertyMultimediaImage);
}
