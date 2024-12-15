package cloud.uwu.realestatebackend.repositories.profile;


import cloud.uwu.realestatebackend.entities.profile.ProfileAsk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileAskRepository extends JpaRepository<ProfileAsk, UUID> {
}
