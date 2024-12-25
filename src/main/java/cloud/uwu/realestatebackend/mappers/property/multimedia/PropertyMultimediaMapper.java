package cloud.uwu.realestatebackend.mappers.property.multimedia;

import cloud.uwu.realestatebackend.dtos.property.multimedia.PropertyMultimediaResponseDTO;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimedia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PropertyMultimediaImageMapper.class, PropertyMultimediaVideoMapper.class})
public interface PropertyMultimediaMapper {
    PropertyMultimediaResponseDTO propertyMultimediaToPropertyMultimediaResponseDTO(PropertyMultimedia propertyMultimedia);
}
