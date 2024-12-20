package cloud.uwu.realestatebackend.repositories.profile;


import cloud.uwu.realestatebackend.entities.profile.ProfileNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProfileNotificationRepository extends JpaRepository<ProfileNotification, UUID> {
    List<ProfileNotification> getProfileNotificationsByProfileId(UUID profileId);
}
