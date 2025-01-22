package cloud.uwu.realestatebackend.repositories.profile;


import cloud.uwu.realestatebackend.entities.profile.ProfileFollow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileFollowRepository extends JpaRepository<ProfileFollow, UUID> {
    Page<ProfileFollow> getProfileFollowsByFollower_Id(UUID followerId, Pageable pageable);
    Page<ProfileFollow> getProfileFollowsByFollowing_Id(UUID followingId, Pageable pageable);
    Optional<ProfileFollow> getProfileFollowByFollowerIdAndFollowingId(UUID followerId, UUID followingId);
}
