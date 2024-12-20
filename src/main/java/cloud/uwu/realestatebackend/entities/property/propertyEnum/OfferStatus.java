package cloud.uwu.realestatebackend.entities.property.propertyEnum;

public enum OfferStatus {
    AVAILABLE,
    RENTED,
    SOLD,
    EXPIRED,
    WITHDRAWN,
    UNDER_OFFER,
    BACK_ON_MARKET // AFTER SELLING / RENTING (IF OFFER APPEARS AGAIN) GAINS 3 DAYS OF "BACK ON MARKET" BOOST
}
