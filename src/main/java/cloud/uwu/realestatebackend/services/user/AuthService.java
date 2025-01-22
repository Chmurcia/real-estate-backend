package cloud.uwu.realestatebackend.services.user;

import cloud.uwu.realestatebackend.dtos.user.user.UserDTO;
import cloud.uwu.realestatebackend.entities.user.User;
import cloud.uwu.realestatebackend.entities.user.UserFlag;
import cloud.uwu.realestatebackend.entities.user.UserRole;
import cloud.uwu.realestatebackend.entities.user.userEnums.Role;
import cloud.uwu.realestatebackend.exceptions.AlreadyExistException;
import cloud.uwu.realestatebackend.exceptions.InvalidPasswordException;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.other.security.jwt.JwtService;
import cloud.uwu.realestatebackend.other.security.jwt.userDetails.CustomUserDetails;
import cloud.uwu.realestatebackend.repositories.user.UserFlagRepository;
import cloud.uwu.realestatebackend.repositories.user.UserRepository;
import cloud.uwu.realestatebackend.repositories.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserFlagRepository userFlagRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserRoleService userRoleService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private final JwtService jwtService;


    public Boolean registerUser(UserDTO userDTO) {

        if (userRepository.findUserByEmail(userDTO.getEmail()).isPresent()) {
            throw new AlreadyExistException("User with the email already exists");
        }

        UserFlag savedUserFlag = userFlagRepository.saveAndFlush(UserFlag.builder()
                .isVerified(false)
                .isBanned(false)
                .isMuted(false)
                .build());

        UserRole savedUserRole = userRoleRepository.saveAndFlush(UserRole.builder()
                .role(Role.USER)
                .build());

        User user = User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .userFlag(savedUserFlag)
                .userRole(savedUserRole)
                .build();

        userRepository.save(user);
        return true;
    }

    public String loginUser(UserDTO userDTO) {
        User foundUser = getUserByEmail(userDTO.getEmail());
        if (foundUser == null) {
            throw new NotFoundException("User with the email was not found");
        }

        if (passwordEncoder.matches(userDTO.getPassword(), foundUser.getPassword())) {
            return jwtService.generateToken(foundUser.getId());
        }

        throw new InvalidPasswordException("Password is invalid");
    }

    //

    public UserDetails getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return new CustomUserDetails(user, userRoleService);
    }

    private User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElse(null);
    }


}
