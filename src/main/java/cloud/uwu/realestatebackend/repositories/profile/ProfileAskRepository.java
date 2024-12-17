package cloud.uwu.realestatebackend.repositories.profile;


import cloud.uwu.realestatebackend.entities.profile.ProfileAsk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileAskRepository extends JpaRepository<ProfileAsk, UUID> {
    Page<ProfileAsk> getProfileAsksByProfileId(UUID profileId, Pageable pageable);
}
