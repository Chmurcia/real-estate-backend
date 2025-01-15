package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyRooms;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.property.PropertyRoomsMapper;
import cloud.uwu.realestatebackend.repositories.property.PropertyRoomsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyRoomsService {
    private final PropertyRoomsRepository propertyRoomsRepository;
    private final PropertyRoomsMapper propertyRoomsMapper;

    public PropertyRoomsResponseDTO getPropertyRoomsById(UUID id) {
        return propertyRoomsMapper
                .propertyRoomsToPropertyRoomsResponseDTO(getPropertyRooms(id));
    }

    public PropertyRooms createPropertyRooms(PropertyRoomsDTO propertyRoomsDTO) {
        PropertyRooms propertyRooms = PropertyRooms.builder()
                .totalRooms(propertyRoomsDTO.getTotalRooms())
                .totalBedrooms(propertyRoomsDTO.getTotalBedrooms())
                .totalKitchens(propertyRoomsDTO.getTotalKitchens())
                .totalBathrooms(propertyRoomsDTO.getTotalBathrooms())
                .totalLivingRooms(propertyRoomsDTO.getTotalLivingRooms())
                .totalDiningRooms(propertyRoomsDTO.getTotalDiningRooms())
                .totalBalconies(propertyRoomsDTO.getTotalBalconies())
                .totalGarages(propertyRoomsDTO.getTotalGarages())
                .totalBasements(propertyRoomsDTO.getTotalBasements())
                .totalAttics(propertyRoomsDTO.getTotalAttics())
                .totalStorageRooms(propertyRoomsDTO.getTotalStorageRooms())
                .totalClosets(propertyRoomsDTO.getTotalClosets())
                .totalTheaterRooms(propertyRoomsDTO.getTotalTheaterRooms())
                .totalSaunas(propertyRoomsDTO.getTotalSaunas())
                .totalHomeOffices(propertyRoomsDTO.getTotalHomeOffices())
                .totalGyms(propertyRoomsDTO.getTotalGyms())
                .totalLibraries(propertyRoomsDTO.getTotalLibraries())
                .build();

        return propertyRoomsRepository.saveAndFlush(propertyRooms);
    }

    public void updatePropertyRooms(UUID id, PropertyRoomsDTO propertyRoomsDTO) {
        PropertyRooms propertyRooms = getPropertyRooms(id);

        if (propertyRoomsDTO.getTotalRooms() != null) {
            propertyRooms.setTotalRooms(propertyRoomsDTO.getTotalRooms());
        } else {
            throw new NullException("total_rooms is null");
        }

        if (propertyRoomsDTO.getTotalBedrooms() != null) {
            propertyRooms.setTotalBedrooms(propertyRoomsDTO.getTotalBedrooms());
        } else {
            throw new NullException("total_bedrooms is null");
        }

        if (propertyRoomsDTO.getTotalKitchens() != null) {
            propertyRooms.setTotalKitchens(propertyRoomsDTO.getTotalKitchens());
        } else {
            throw new NullException("total_kitchens is null");
        }

        if (propertyRoomsDTO.getTotalBathrooms() != null) {
            propertyRooms.setTotalBathrooms(propertyRoomsDTO.getTotalBathrooms());
        } else {
            throw new NullException("total_bathrooms is null");
        }

        if (propertyRoomsDTO.getTotalLivingRooms() != null) {
            propertyRooms.setTotalLivingRooms(propertyRoomsDTO.getTotalLivingRooms());
        } else {
            throw new NullException("total_living_rooms is null");
        }

        if (propertyRoomsDTO.getTotalDiningRooms() != null) {
            propertyRooms.setTotalDiningRooms(propertyRoomsDTO.getTotalDiningRooms());
        } else {
            throw new NullException("total_dining_rooms is null");
        }

        if (propertyRoomsDTO.getTotalBalconies() != null) {
            propertyRooms.setTotalBalconies(propertyRoomsDTO.getTotalBalconies());
        } else {
            throw new NullException("total_balconies is null");
        }

        if (propertyRoomsDTO.getTotalGarages() != null) {
            propertyRooms.setTotalGarages(propertyRoomsDTO.getTotalGarages());
        } else {
            throw new NullException("total_garages is null");
        }

        if (propertyRoomsDTO.getTotalBasements() != null) {
            propertyRooms.setTotalBasements(propertyRoomsDTO.getTotalBasements());
        } else {
            throw new NullException("total_basements is null");
        }

        if (propertyRoomsDTO.getTotalAttics() != null) {
            propertyRooms.setTotalAttics(propertyRoomsDTO.getTotalAttics());
        } else {
            throw new NullException("total_attics is null");
        }

        if (propertyRoomsDTO.getTotalStorageRooms() != null) {
            propertyRooms.setTotalStorageRooms(propertyRoomsDTO.getTotalStorageRooms());
        } else {
            throw new NullException("total_storage_rooms is null");
        }

        if (propertyRoomsDTO.getTotalClosets() != null) {
            propertyRooms.setTotalClosets(propertyRoomsDTO.getTotalClosets());
        } else {
            throw new NullException("total_closets is null");
        }

        if (propertyRoomsDTO.getTotalTheaterRooms() != null) {
            propertyRooms.setTotalTheaterRooms(propertyRoomsDTO.getTotalTheaterRooms());
        } else {
            throw new NullException("total_theater_rooms is null");
        }

        if (propertyRoomsDTO.getTotalSaunas() != null) {
            propertyRooms.setTotalSaunas(propertyRoomsDTO.getTotalSaunas());
        } else {
            throw new NullException("total_saunas is null");
        }

        if (propertyRoomsDTO.getTotalHomeOffices() != null) {
            propertyRooms.setTotalHomeOffices(propertyRoomsDTO.getTotalHomeOffices());
        } else {
            throw new NullException("total_home_offices is null");
        }

        if (propertyRoomsDTO.getTotalGyms() != null) {
            propertyRooms.setTotalGyms(propertyRoomsDTO.getTotalGyms());
        } else {
            throw new NullException("total_gyms is null");
        }

        if (propertyRoomsDTO.getTotalLibraries() != null) {
            propertyRooms.setTotalLibraries(propertyRoomsDTO.getTotalLibraries());
        } else {
            throw new NullException("total_libraries is null");
        }

        propertyRoomsRepository.save(propertyRooms);

    }

    public void patchPropertyRooms(
            UUID id, PropertyRoomsPatchDTO propertyRoomsPatchDTO
    ) {
        PropertyRooms propertyRooms = getPropertyRooms(id);

        if (propertyRoomsPatchDTO.getTotalRooms() != null) {
            propertyRooms.setTotalRooms(propertyRoomsPatchDTO.getTotalRooms());
        }

        if (propertyRoomsPatchDTO.getTotalBedrooms() != null) {
            propertyRooms.setTotalBedrooms(propertyRoomsPatchDTO.getTotalBedrooms());
        }

        if (propertyRoomsPatchDTO.getTotalKitchens() != null) {
            propertyRooms.setTotalKitchens(propertyRoomsPatchDTO.getTotalKitchens());
        }
        if (propertyRoomsPatchDTO.getTotalBathrooms() != null) {
            propertyRooms.setTotalBathrooms(propertyRoomsPatchDTO.getTotalBathrooms());
        }

        if (propertyRoomsPatchDTO.getTotalLivingRooms() != null) {
            propertyRooms.setTotalLivingRooms(propertyRoomsPatchDTO.getTotalLivingRooms());
        }

        if (propertyRoomsPatchDTO.getTotalDiningRooms() != null) {
            propertyRooms.setTotalDiningRooms(propertyRoomsPatchDTO.getTotalDiningRooms());
        }

        if (propertyRoomsPatchDTO.getTotalBalconies() != null) {
            propertyRooms.setTotalBalconies(propertyRoomsPatchDTO.getTotalBalconies());
        }

        if (propertyRoomsPatchDTO.getTotalGarages() != null) {
            propertyRooms.setTotalGarages(propertyRoomsPatchDTO.getTotalGarages());
        }

        if (propertyRoomsPatchDTO.getTotalBasements() != null) {
            propertyRooms.setTotalBasements(propertyRoomsPatchDTO.getTotalBasements());
        }

        if (propertyRoomsPatchDTO.getTotalAttics() != null) {
            propertyRooms.setTotalAttics(propertyRoomsPatchDTO.getTotalAttics());
        }

        if (propertyRoomsPatchDTO.getTotalStorageRooms() != null) {
            propertyRooms.setTotalStorageRooms(propertyRoomsPatchDTO.getTotalStorageRooms());
        }

        if (propertyRoomsPatchDTO.getTotalClosets() != null) {
            propertyRooms.setTotalClosets(propertyRoomsPatchDTO.getTotalClosets());
        }

        if (propertyRoomsPatchDTO.getTotalTheaterRooms() != null) {
            propertyRooms.setTotalTheaterRooms(propertyRoomsPatchDTO.getTotalTheaterRooms());
        }

        if (propertyRoomsPatchDTO.getTotalSaunas() != null) {
            propertyRooms.setTotalSaunas(propertyRoomsPatchDTO.getTotalSaunas());
        }

        if (propertyRoomsPatchDTO.getTotalHomeOffices() != null) {
            propertyRooms.setTotalHomeOffices(propertyRoomsPatchDTO.getTotalHomeOffices());
        }

        if (propertyRoomsPatchDTO.getTotalGyms() != null) {
            propertyRooms.setTotalGyms(propertyRoomsPatchDTO.getTotalGyms());
        }

        if (propertyRoomsPatchDTO.getTotalLibraries() != null) {
            propertyRooms.setTotalLibraries(propertyRoomsPatchDTO.getTotalLibraries());
        }

        propertyRoomsRepository.save(propertyRooms);

    }

    public void deletePropertyRooms(UUID id) {
        getPropertyRooms(id);

        propertyRoomsRepository.deleteById(id);
    }

    //

    private PropertyRooms getPropertyRooms(UUID id) {
        return propertyRoomsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyRooms not found"));
    }
}

