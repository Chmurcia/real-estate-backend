package cloud.uwu.realestatebackend.repositories.user;

import cloud.uwu.realestatebackend.entities.user.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification, UUID> {
    List<UserNotification> getUserNotificationsByUserId(UUID userId);
}
