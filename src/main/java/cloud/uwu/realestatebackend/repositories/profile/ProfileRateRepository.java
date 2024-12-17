package cloud.uwu.realestatebackend.repositories.profile;

import cloud.uwu.realestatebackend.entities.profile.ProfileRate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProfileRateRepository extends JpaRepository<ProfileRate, UUID> {
    Page<ProfileRate> getProfileRatesByProfileId(UUID profileId, Pageable pageable);
}
