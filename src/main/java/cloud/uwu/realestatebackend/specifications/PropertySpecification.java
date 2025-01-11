package cloud.uwu.realestatebackend.specifications;

import cloud.uwu.realestatebackend.dtos.other.filters.PropertyFilterDTO;
import cloud.uwu.realestatebackend.entities.property.Property;
import cloud.uwu.realestatebackend.entities.property.PropertyAreas;
import cloud.uwu.realestatebackend.entities.property.PropertyDetails;
import cloud.uwu.realestatebackend.entities.property.PropertyRooms;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyCounts;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PropertySpecification {
    public static Specification<Property> createSpecification(PropertyFilterDTO filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Property, PropertyStatistics> statisticsJoin = root.join("statistics", JoinType.LEFT);

            Join<PropertyStatistics, PropertyCounts> countsJoin = statisticsJoin.join("counts", JoinType.LEFT);

            Join<Property, PropertyDetails> detailsJoin = root.join("details", JoinType.LEFT);

            Join<PropertyDetails, PropertyAreas> areasJoin = detailsJoin.join("areas", JoinType.LEFT);

            Join<PropertyDetails, PropertyRooms> roomsJoin = detailsJoin.join("rooms", JoinType.LEFT);

            if (filters.getCountry() != null) {
                predicates.add(criteriaBuilder.equal(root.get("country"), filters.getCountry()));
            }

            if (filters.getState() != null) {
                predicates.add(criteriaBuilder.equal(root.get("state"), filters.getState()));
            }

            if (filters.getCity() != null) {
                predicates.add(criteriaBuilder.equal(root.get("city"), filters.getCity()));
            }

            if (filters.getMinPrice() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), filters.getMinPrice()));
            }

            if (filters.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), filters.getMaxPrice()));
            }

            if (filters.getYearBuilt() != null) {
                predicates.add(criteriaBuilder.equal(root.get("yearBuilt"), filters.getYearBuilt()));
            }

            if (filters.getMinRating() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(statisticsJoin.get("rating"), filters.getMinRating()));
            }

            if (filters.getMaxRating() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(statisticsJoin.get("rating"), filters.getMaxRating()));
            }

            if (filters.getMinLikes() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(countsJoin.get("likes"), filters.getMinLikes()));
            }

            if (filters.getMaxLikes() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(countsJoin.get("likes"), filters.getMaxLikes()));
            }

            if (filters.getMinDislikes() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(countsJoin.get("dislikes"), filters.getMinDislikes()));
            }

            if (filters.getMaxDislikes() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(countsJoin.get("dislikes"), filters.getMaxDislikes()));
            }

            if (filters.getMinVisits() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(countsJoin.get("visits"), filters.getMinVisits()));
            }

            if (filters.getMaxVisits() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(countsJoin.get("visits"), filters.getMaxVisits()));
            }

            if (filters.getMinTotalArea() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(areasJoin.get("totalArea"), filters.getMinTotalArea()));
            }

            if (filters.getMaxTotalArea() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(areasJoin.get("totalArea"), filters.getMaxTotalArea()));
            }

            if (filters.getMinBuildingArea() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(areasJoin.get("buildingArea"), filters.getMinBuildingArea()));
            }

            if (filters.getMaxBuildingArea() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(areasJoin.get("buildingArea"), filters.getMaxBuildingArea()));
            }

            if (filters.getMinLivingArea() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(areasJoin.get("livingArea"), filters.getMinLivingArea()));
            }

            if (filters.getMaxLivingArea() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(areasJoin.get("livingArea"), filters.getMaxLivingArea()));
            }

            if (filters.getOfferStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("offerStatus"), filters.getOfferStatus()));
            }

            if (filters.getConditionStatus() != null) {
                predicates.add(criteriaBuilder.equal(detailsJoin.get("conditionStatus"), filters.getConditionStatus()));
            }

            if (filters.getPropertyType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("propertyType"), filters.getPropertyType()));
            }

            if (filters.getMinTotalRooms() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(roomsJoin.get("totalRooms"), filters.getMinTotalRooms()));
            }

            if (filters.getMaxTotalRooms() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(roomsJoin.get("totalRooms"), filters.getMaxTotalRooms()));
            }

            if (filters.getMinTotalBedrooms() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(roomsJoin.get("totalBedrooms"), filters.getMinTotalBedrooms()));
            }

            if (filters.getMaxTotalBedrooms() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(roomsJoin.get("totalBedrooms"), filters.getMaxTotalBedrooms()));
            }

            if (filters.getMinTotalKitchens() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(roomsJoin.get("totalKitchens"), filters.getMinTotalKitchens()));
            }

            if (filters.getMaxTotalKitchens() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(roomsJoin.get("totalKitchens"), filters.getMaxTotalKitchens()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
