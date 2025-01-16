package cloud.uwu.realestatebackend.specifications;

import cloud.uwu.realestatebackend.dtos.other.filters.ProfileFilterDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;


public class ProfileSpecification {
    public static Specification<Profile> createSpecification(ProfileFilterDTO filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getNickName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nickName"), "%" + filters.getNickName() + "%"));
            }

            if (filters.getCountry() != null) {
                predicates.add(criteriaBuilder.equal(root.get("country"), filters.getCountry()));
            }

            if (filters.getState() != null) {
                predicates.add(criteriaBuilder.equal(root.get("state"), filters.getState()));
            }

            if (filters.getCity() != null) {
                predicates.add(criteriaBuilder.equal(root.get("city"), filters.getCity()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
