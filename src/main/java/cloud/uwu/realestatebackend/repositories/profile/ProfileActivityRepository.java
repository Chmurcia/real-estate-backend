package cloud.uwu.realestatebackend.repositories.profile;


import cloud.uwu.realestatebackend.entities.profile.ProfileActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileActivityRepository extends JpaRepository<ProfileActivity, UUID> {
    Page<ProfileActivity> getProfileActivitiesByProfileId(UUID profileId, Pageable pageable);
}
