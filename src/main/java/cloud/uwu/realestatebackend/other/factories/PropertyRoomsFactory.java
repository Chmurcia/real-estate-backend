package cloud.uwu.realestatebackend.other.factories;

import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyRooms;

public class PropertyRoomsFactory {
    public static PropertyRooms createPropertyRooms() {
        return PropertyRooms.builder()
                .totalRooms(31)
                .totalBedrooms(5)
                .totalKitchens(1)
                .totalBathrooms(3)
                .totalLivingRooms(2)
                .totalDiningRooms(2)
                .totalBalconies(5)
                .totalGarages(2)
                .totalBasements(1)
                .totalAttics(1)
                .totalStorageRooms(2)
                .totalClosets(3)
                .totalTheaterRooms(0)
                .totalSaunas(0)
                .totalHomeOffices(2)
                .totalGyms(1)
                .totalLibraries(1)
                .build();
    }

    public static PropertyRoomsDTO createPropertyRoomsDTOToCreate() {
        return PropertyRoomsDTO.builder()
                .totalRooms(31)
                .totalBedrooms(5)
                .totalKitchens(1)
                .totalBathrooms(3)
                .totalLivingRooms(2)
                .totalDiningRooms(2)
                .totalBalconies(5)
                .totalGarages(2)
                .totalBasements(1)
                .totalAttics(1)
                .totalStorageRooms(2)
                .totalClosets(3)
                .totalTheaterRooms(0)
                .totalSaunas(0)
                .totalHomeOffices(2)
                .totalGyms(1)
                .totalLibraries(1)
                .build();
    }

    public static PropertyRoomsDTO createPropertyRoomsDTOToUpdate() {
        return PropertyRoomsDTO.builder()
                .totalRooms(64)
                .totalBedrooms(11)
                .totalKitchens(3)
                .totalBathrooms(7)
                .totalLivingRooms(9)
                .totalDiningRooms(3)
                .totalBalconies(2)
                .totalGarages(4)
                .totalBasements(2)
                .totalAttics(3)
                .totalStorageRooms(3)
                .totalClosets(5)
                .totalTheaterRooms(1)
                .totalSaunas(1)
                .totalHomeOffices(3)
                .totalGyms(5)
                .totalLibraries(2)
                .build();
    }

    public static PropertyRoomsResponseDTO createPropertyRoomsResponseDTO() {
        return PropertyRoomsResponseDTO.builder()
                .totalRooms(31)
                .totalBedrooms(5)
                .totalKitchens(1)
                .totalBathrooms(3)
                .totalLivingRooms(2)
                .totalDiningRooms(2)
                .totalBalconies(5)
                .totalGarages(2)
                .totalBasements(1)
                .totalAttics(1)
                .totalStorageRooms(2)
                .totalClosets(3)
                .totalTheaterRooms(0)
                .totalSaunas(0)
                .totalHomeOffices(2)
                .totalGyms(1)
                .totalLibraries(1)
                .build();
    }

    public static PropertyRoomsPatchDTO createPropertyRoomsPatchDTO() {
        return PropertyRoomsPatchDTO.builder()
                .totalRooms(39)
                .totalBedrooms(7)
                .totalKitchens(1)
                .totalBathrooms(3)
                .totalLivingRooms(5)
                .totalDiningRooms(2)
                .totalBalconies(5)
                .totalGarages(2)
                .totalBasements(1)
                .totalAttics(1)
                .totalStorageRooms(2)
                .totalClosets(3)
                .totalTheaterRooms(0)
                .totalSaunas(3)
                .totalHomeOffices(2)
                .totalGyms(1)
                .totalLibraries(1)
                .build();
    }
}
